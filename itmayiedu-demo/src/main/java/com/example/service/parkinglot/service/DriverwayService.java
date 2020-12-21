package com.example.service.parkinglot.service;

import com.example.service.parkinglot.dao.DriverwayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DriverwayService {
    @Autowired
    private DriverwayDao driverwayDao;

    /**
     * 查询停车场所有出入口列表
     * @param params
     * @return
     */
    public List<Map<String,Object>> listDriverways(Map<String,String> params){

        return driverwayDao.listDriverways(params);
    }

    /**
     * 查询停车场 出/入 口列表
     * @param params
     * @return
     */
    public List<Map<String,Object>> listDriverwaysByType(Map<String,Object> params){

        return driverwayDao.listDriverwaysByType(params);
    }

    /**
     * 查询停车场 已停用 出入口列表
     * @param params
     * @return
     */
    public List<Map<String,Object>> listDriverwaysByStatus(Map<String,Object> params){

        return driverwayDao.listDriverwaysByStatus(params);
    }

    /**
     * 更改停车场道闸状态
     * @param params
     */
    public void updateDriverwayStatus(Map<String,Object> params){

        driverwayDao.updateDriverwayStatus(params);
    }
}
