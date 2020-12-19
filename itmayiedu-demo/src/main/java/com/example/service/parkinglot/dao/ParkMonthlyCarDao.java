package com.example.service.parkinglot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ParkMonthlyCarDao {
    /**
     * 模糊查询 包月车辆信息
     * @param params
     * @return
     */
    List<Map<String,Object>> listParkMonthlyCar(Map<String,Object> params);

    /**
     * 获取包月车辆信息总数 用于分页
     * @param params
     * @return
     */
    int getCountParkMonthlyCar(Map<String, Object> params);

    /**
     * 包月车辆占用情况
     * @param params
     * @return
     */
    List<Map<String,Object>> listParkMonthlyCarInfo(Map<String,Object> params);

    /**
     * 添加包月用户车辆信息
     * @param params
     */
    void saveParkMonthlyCar(Map<String,Object> params);

    /**
     * 更新包月车辆信息
     * @param params
     */
    void updateParkMonthlyCarInfo(Map<String,Object> params);

    /**
     * 删除一个包月车辆信息
     * @param params
     */
    void deleteParkMonthlyCar(Map<String,Object> params);
}
