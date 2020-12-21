package com.example.service.parkinglot.service;

import com.example.service.parkinglot.dao.ParkMonthlyExtendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParkMonthlyExtendService {
    @Autowired
    private ParkMonthlyExtendDao parkMonthlyExtendDao;

    /**
     * 获取总条数
     * @param params
     * @return
     */
    public int getCountMonthlyBySum(Map<String,Object> params){
        return parkMonthlyExtendDao.getCountMonthlyBySum(params);
    }
    public int getCountMonthlyByGeneral (Map<String,Object> params){
        return parkMonthlyExtendDao.getCountMonthlyByGeneral(params);
    }
    public int getCountMonthlyBySubsection (Map<String,Object> params){
        return parkMonthlyExtendDao.getCountMonthlyBySubsection(params);
    }
    public int getCountMonthlyBySoondue  (Map<String,Object> params){
        return parkMonthlyExtendDao.getCountMonthlyBySoondue(params);
    }
    public int getCountMonthlyByOverdue  (Map<String,Object> params){
        return parkMonthlyExtendDao.getCountMonthlyByOverdue(params);
    }

    /**
     * 初始化续费用户信息
     * @param params
     * @return
     */
    public Map<String,Object> getMonthlyInfo(Map<String,Object> params){
        return parkMonthlyExtendDao.getMonthlyInfo(params);
    }

    /**
     * 保存包月车辆信息到期 到 临时表
     * @param params
     */
    public void saveMonthlyCarInfo(Map<String,Object> params){
        parkMonthlyExtendDao.saveMonthlyCarInfo(params);
    }

    /**
     * 获取 即将到期的时间
     * @param params
     * @return
     */
    public List<Map<String,Object>> listNearEndDate(Map<String,Object> params){
        return parkMonthlyExtendDao.listNearEndDate(params);
    }

    /**
     * 初始化 去设置 页面信息
     * @param params
     * @return
     */
    public List<Map<String,Object>> getMonthlyCarInit(Map<String,Object> params){
        return parkMonthlyExtendDao.getMonthlyCarInit(params);
    }

//    /**
//     * 更新 去设置 页面信息
//     * @param params
//     */
//    public void updateMonthlyCarInfo(Map<String,Object> params){
//        parkMonthlyExtendDao.updateMonthlyCarInfo(params);
//    }

    /**
     * 导出 去设置页面信息
     * @param params
     * @return
     */
    public List<Map<String,Object>> exportMonthlyCarInfo(Map<String,Object> params){
        return parkMonthlyExtendDao.exportMonthlyCarInfo(params);
    }

    /**
     * 删除临时表数据
     * @param params
     */
    public void deleteMonthlyCarTemp(Map<String,Object> params){
        parkMonthlyExtendDao.deleteMonthlyCarTemp(params);
    }

    /**
     * 保存续费记录
     * @param params
     */
    public void saveParkMonthlyExtends(Map<String,Object> params){
        parkMonthlyExtendDao.saveParkMonthlyExtends(params);
    }

    /**
     * 如果统一设置 则统一更新
     * @param params
     */
    public void updateParkMonthlyCarUnified(Map<String,Object> params){
        parkMonthlyExtendDao.updateParkMonthlyCarUnified(params);
    }

    /**
     * 如果分别设置 则联表更新
     * @param params
     */
    public void updateParkMonthlyCarByTempCar(Map<String,Object> params){
        parkMonthlyExtendDao.updateParkMonthlyCarByTempCar(params);
    }


}
