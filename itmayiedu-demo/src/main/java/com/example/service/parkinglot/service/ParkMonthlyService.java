package com.example.service.parkinglot.service;

import com.example.service.parkinglot.dao.ParkMonthlyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParkMonthlyService {

    @Autowired
    ParkMonthlyDao parkMonthlyDao;

    /**
     * 根据停车场ID和查询条件加载包月信息（分页）
     * @param params
     * @return
     */
    public List<Map<String,Object>> listParkMonthly(Map<String,Object> params){
        return parkMonthlyDao.listParkMonthly(params);
    }

    /**
     * 查询包月总记录数 用于分页计算
     * @param params
     * @return
     */
    public int getCountParkMonthly(Map<String,Object> params){
        return parkMonthlyDao.getCountParkMonthly(params);
    }

    /**
     * 根据停车场ID和查询条件导出包月信息
     * @param params
     * @return
     */
    public List<Map<String,Object>> exportParkMonthly(Map<String,Object> params){
        return parkMonthlyDao.exportParkMonthly(params);
    }

    /**
     * 保存包月用户基本信息
     * @param params
     * @return
     */
    public void saveParkMonthly(Map<String,Object> params){
        parkMonthlyDao.saveParkMonthly(params);
    }

    /**
     * 修改包月用户基本信息
     * @param params
     * @return
     */
    public void updateParkMonthly(Map<String,Object> params){
        parkMonthlyDao.updateParkMonthly(params);
    }


    /**
     * 删除包月基本信息
     * @param params
     * @return
     */
    public void deleteParkMonthly(Map<String,Object> params){
        parkMonthlyDao.deleteParkMonthly(params);
    }

    /**
     * 保存包月车辆信息
     * @param params
     */
    public void saveParkMonthlyOccupy(Map<String,Object> params){
        parkMonthlyDao.saveParkMonthlyOccupy(params);
    }

    /**
     * 修改包月车辆信息
     * @param params
     */
    public void updateParkMonthlyOccupy(Map<String,Object> params){
        parkMonthlyDao.updateParkMonthlyOccupy(params);
    }










}
