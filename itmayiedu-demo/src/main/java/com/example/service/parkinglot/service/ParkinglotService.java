package com.example.service.parkinglot.service;

import com.example.service.parkinglot.dao.ParkinglotDao;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParkinglotService {

	@Autowired
	private ParkinglotDao parkinglotDao;

	/**
	 * 查询总记录数 用于分页计算
	 *
	 * @param params
	 * @return
	 */
	public int getCountparkinglot(Map<String, Object> params) {
		return parkinglotDao.getCountParkinglot(params);
	}

	/**
	 * 根据登录用户权限加载停车场数据
	 *
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> listParkinglot(Map<String, Object> params) {
		return parkinglotDao.listParkinglot(params);
	}

	/**
	 * 通过ID来查询停车场信息
	 *
	 * @param parkinglotId
	 * @return
	 */
	public Map<String, Object> getParkinglot(String parkinglotId) {
		return parkinglotDao.getParkinglot(parkinglotId);
	}

	/**
	 * 通过参数来管理停车场
	 *
	 * @param params
	 * @return
	 */
	public void updateParkinglotFlags(Map<String, Object> params) {
		parkinglotDao.updateParkinglotFlags(params);
	}

	/**
	 * 查询嵌套停车场 （下拉框)
	 *
	 * @param parparentParkinglotId
	 * @return
	 */
	public List<Map<String, Object>> listNestParkinglotClasicInfo(String parparentParkinglotId) {
		return parkinglotDao.listNestParkinglotClasicInfo(parparentParkinglotId);
	}

	/**
	 * 停车场统计营业总览
	 *
	 * @param params
	 * @return
	 */
	public Map<String, Object> getParkLotCount(Map<String, Object> params) {
		return parkinglotDao.getParkLotCount(params);
	}

	/**
	 * 查询商户充值所需停车场信息
	 *
	 * @param params
	 * @return
	 */
	public Map<String, Object> getMerchantsParking(Map<String, Object> params) {
		return parkinglotDao.getMerchantsParking(params);
	}

}
