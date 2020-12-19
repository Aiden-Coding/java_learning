package com.example.service.parkinglot.controller;


import com.example.service.parkinglot.service.SpecialCarService;
import com.example.service.parkinglot.util.DatabaseUtils;
import com.example.service.parkinglot.util.Result;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SpecialCarController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpecialCarService specialCarService;

    /**
     * 特殊车辆，根据specialtype查询免费车辆或者黑名车辆
     */
    @RequestMapping(value = "/listSpecialCar", method = RequestMethod.POST)
    public Result listSpecialCar(@RequestParam Map<String, Object> params) {
.
        logger.info("SpecialCarController: listSpecialCar is accessed.");

        //获取参数
        int pageSize = Integer.valueOf(params.get("pageSize").toString());
        //计算当前页面
        int currentPage = Integer.valueOf(params.get("currentPage").toString());
        int startRow = (currentPage - 1) * pageSize;
        //封装参数
        params.put("startRow", startRow);
        params.put("pageSize", pageSize);

        //获取总页数
        int totalPage = specialCarService.getCountSpecialCarLot(params);
        //执行查询方法
        List<Map<String, Object>> list = specialCarService.listSpecialCar(params);
        return Result.buildResult(Result.Status.OK, "OK", totalPage, list);
    }

    /**
     * 特殊车辆删除
     **/
    @RequestMapping(value = "/deleteSpecialCar", method = RequestMethod.POST)
    public Result deleteSpecialCar(@RequestParam Map<String, String> params) {
        logger.info("SpecialCarController: deleteSpecialCar is accessed.");
        //获取参数
        String specialCarId = params.get("specialCarId");
        //非空验证
        if (StringUtils.isEmpty(specialCarId)) {
            logger.info("WebLoginController : rulesId is empty , return BAD_REQUEST.");
            return Result.buildResult(Result.Status.BAD_REQUEST, "carId is empty", null);
        }
        //执行
        specialCarService.deleteSpecialCar(params);
        return Result.buildResult(Result.Status.OK, "OK");
    }

    /**
     * 更新对应特殊车辆
     */
    @RequestMapping(value = "/updateSpecialCar", method = RequestMethod.POST)
    public Result updateSpecialCar(@RequestParam Map<String, String> params) {
        logger.info("SpecialCarController: updateSpecialCar is accessed.");
        //判断不为空则执行更新
        if (params != null && !params.isEmpty()) {
            String startDate = params.get("startDate").toString();
            //因为DateTime类型插入空值会报错所以做此判断
            if (startDate.length() == 0) {
                params.remove("startDate");
                params.remove("endDate");
            }
            specialCarService.updateSpecialCar(params);
        }
        return Result.buildResult(Result.Status.OK, "OK");
    }

    /**
     * 添加特殊车辆
     *
     * @param params 参数列表
     */
    @RequestMapping(value = "/saveSpecialCar", method = RequestMethod.POST)
    public Result saveSpecialCar(@RequestParam Map<String, Object> params) {
        logger.info("SpecialCarController: saveSpecialCar is accessed.");
        if (params != null && !params.isEmpty()) {
            String specialCarId = DatabaseUtils.generateUniqueKey();
            String startDate = params.get("startDate").toString();
            //因为DateTime类型插入空值会报错所以做此判断
            if (startDate.length() == 0) {
                params.remove("startDate");
                params.remove("endDate");
            }
            params.put("specialCarId", specialCarId);
            specialCarService.saveSpecialCar(params);
        }
        return Result.buildResult(Result.Status.OK, "OK");
    }


}

