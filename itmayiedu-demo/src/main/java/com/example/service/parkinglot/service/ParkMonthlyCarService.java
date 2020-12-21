package com.example.service.parkinglot.service;

import com.example.service.parkinglot.dao.ParkMonthlyCarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParkMonthlyCarService {

    @Autowired
    private ParkMonthlyCarDao parkMonthlyCarDao;
    /**
     * 查询包月车辆信息列表
     * @param params
     * @return
     */
    public List<Map<String,Object>> listParkMonthlyCar(Map<String,Object> params){
        return parkMonthlyCarDao.listParkMonthlyCar(params);
    }

    /**
     * 获取包月车辆信息总数 用于分页
     * @param params
     * @return
     */
    public int getCountParkMonthlyCar(Map<String, Object> params){
        return parkMonthlyCarDao.getCountParkMonthlyCar(params);
    }

    /**
     * 包月车辆占用情况
     * @param params
     * @return
     */
    public List<Map<String,Object>> listParkMonthlyCarInfo(Map<String,Object> params){
        return parkMonthlyCarDao.listParkMonthlyCarInfo(params);
    }

    /**
     * 添加包月用户车辆信息
     * @param params
     */
    public void saveParkMonthlyCar(Map<String,Object> params){
        parkMonthlyCarDao.saveParkMonthlyCar(params);
    }


    /**
     * 修改当前包月车辆信息
     * @param params
     */
    public void updateParkMonthlyCarInfo(Map<String,Object> params){
        parkMonthlyCarDao.updateParkMonthlyCarInfo(params);
    }

    /**
     * 删除一个包月车辆信息
     * @param params
     */
    public void deleteParkMonthlyCar(Map<String,Object> params){
        parkMonthlyCarDao.deleteParkMonthlyCar(params);
    }

}
