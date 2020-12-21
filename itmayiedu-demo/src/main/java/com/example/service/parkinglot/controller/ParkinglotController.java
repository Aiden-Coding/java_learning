package com.example.service.parkinglot.controller;

import com.alibaba.fastjson.JSON;
import com.example.service.parkinglot.feign.MsSystemApi;
import com.example.service.parkinglot.service.ParkinglotService;
import com.example.service.parkinglot.util.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.service.parkinglot.bo.UserPermissionsInfo;

@RestController
public class ParkinglotController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ParkinglotService parkinglotService;

	@Autowired
	private MsSystemApi msSystemApi;

	/**
	 * 根据登录用户权限加载停车场数据（分页）
	 * 
	 * @param params(当前页)
	 * @return
	 */
	@RequestMapping(value = "/listParkinglot", method = RequestMethod.POST)
	public Result listParkinglot(@RequestParam Map<String, Object> params) {

		if (null == params.get("uid").toString()) {
			return Result.buildResult(Result.Status.BAD_REQUEST, "uid cant be null");
		}

		logger.info("ParkinglotController: listParkinglot is accessed.");

		// 使用 feign 调用系统微服务，注意这里直接 get “data”的内容。
		String userUnitInfoJson = JSON.toJSONString(msSystemApi.getUserPermissionsInfo(params).get("data"));

		// 转换为业务对象
		UserPermissionsInfo userPermissionsInfo = JSON.parseObject(userUnitInfoJson, UserPermissionsInfo.class);

		// 将单位类别添加到参数列表中
		if (1 == userPermissionsInfo.getIsSysAdmin()) {
			params.put("searchUnitType", -1);
		} else {
			params.put("searchUnitType", userPermissionsInfo.getUnitType());
		}

		// 将品牌ID添加到参数列表中
		params.put("brandId", userPermissionsInfo.getBrandId());
		// 查询数据总条数
		int totalRows = parkinglotService.getCountparkinglot(params);
		// 计算当前页面
		int currentPage = Integer.parseInt(params.get("currentPage").toString());
		int pageSize = Integer.parseInt(params.get("pageSize").toString());
		int startRow = (currentPage - 1) * pageSize;

		// 将分页参数添加到参数列表中
		params.put("startRow", startRow);
		params.put("pageSize", pageSize);

		// 查询分页结果
		List<Map<String, Object>> parkinglotList = parkinglotService.listParkinglot(params);

		return Result.buildResult(Result.Status.OK, "OK", totalRows, parkinglotList);
	}

	/**
	 * 通过 停车场id 获取停车场信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getParkinglotInfo", method = RequestMethod.POST)
	public Result getParkinglotInfo(@RequestParam Map<String, Object> params) {
		logger.info("ParkinglotController: getParkinglotInfo is accessed.");
		// TODO 用户权限验证
		String parkinglotId = params.get("parkinglotId").toString();

		Map<String, Object> result = parkinglotService.getParkinglot(parkinglotId);

		return Result.buildResult(Result.Status.OK, result);
	}

	/**
	 * 停车场管理服务
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/updateFlags", method = RequestMethod.POST)
	public Result updateParkinglotFlags(@RequestParam Map<String, Object> params) {
		logger.info("ParkinglotController: updateParkinglotFlags is accessed.");

		String parkId = params.get("parkId").toString();
		String fileType = params.get("fileType").toString();
		String fileValue = params.get("fileValue").toString();
		String uid = params.get("uid").toString();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("parkId", parkId);
		map.put("uid", uid);
		map.put("fileType", fileType);
		map.put("fileValue", fileValue);

		parkinglotService.updateParkinglotFlags(map);

		return Result.buildResult(Result.Status.OK, "Ok");
	}

	/**
	 * 查询嵌套停车场
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/getNestParkinglot", method = RequestMethod.POST)
	public Result listNestParkinglotClasicInfo(@RequestParam Map<String, Object> params) {
		logger.info("ParkinglotController: listNestParkinglotClasicInfo is accessed.");

		String parkinglotId = params.get("parkinglotId").toString();
		List<Map<String, Object>> nestParkinglots = parkinglotService.listNestParkinglotClasicInfo(parkinglotId);

		return Result.buildResult(Result.Status.OK, "OK", nestParkinglots);
	}

	/**
	 * 停车场统计营业总览
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/getParkLotCount", method = RequestMethod.POST)
	public Result getParkLotCount(@RequestParam Map<String, Object> params) {
		logger.info("ParkinglotController: getParkLotCount is accessed.");
		Map<String, Object> result = parkinglotService.getParkLotCount(params);

		return Result.buildResult(Result.Status.OK, "OK", result);
	}

	/**
	 * 查询商户充值所需停车场信息
	 * 
	 * @param params
	 * @return
	 */

	@RequestMapping(value = "/getMerchantsParking", method = RequestMethod.POST)
	public Result getMerchantsParking(@RequestParam Map<String, Object> params) {
		logger.info("ParkinglotController: getMerchantsParking is accessed.");

		params = parkinglotService.getMerchantsParking(params);

		return Result.buildResult(Result.Status.OK, "OK", params);
	}

}
