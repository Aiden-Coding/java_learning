package com.example.service.parkinglot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ParkMonthlyExtendDao {

    /**
     * 获取总条数
     * @param params
     * @return
     */
    int getCountMonthlyBySum(Map<String,Object> params);
    int getCountMonthlyByGeneral (Map<String,Object> params);
    int getCountMonthlyBySubsection (Map<String,Object> params);
    int getCountMonthlyBySoondue  (Map<String,Object> params);
    int getCountMonthlyByOverdue  (Map<String,Object> params);
    /**
     * 初始化续费用户信息
      * @param params
     * @return
     */
    Map<String,Object> getMonthlyInfo(Map<String,Object> params);

    /**
     * 初始化 去设置 页面信息
     * @param params
     * @return
     */
    List<Map<String,Object>> getMonthlyCarInit(Map<String,Object> params);
    /**
     * 保存 去设置页面信息 到 临时表
     * @param params
     */
    void saveMonthlyCarInfo(Map<String,Object> params);

    /**
     * 导出 去设置页面信息
     * @param params
     * @return
     */
    List<Map<String,Object>> exportMonthlyCarInfo(Map<String,Object> params);

    /**
     * 获取 即将到期的时间
     * @param params
     * @return
     */
    List<Map<String,Object>> listNearEndDate(Map<String,Object> params);

//    /**
//     * 更新 去设置 页面信息
//     * @param params
//     */
//    void updateMonthlyCarInfo(Map<String,Object> params);

    /**
     * 删除临时表数据
     * @param params
     */
    void deleteMonthlyCarTemp(Map<String,Object> params);

    /**
     * 保存续费记录
     * @param params
     */
    void saveParkMonthlyExtends(Map<String,Object> params);

    /**
     * 如果统一设置 则统一更新
     * @param params
     */
    void updateParkMonthlyCarUnified(Map<String,Object> params);

    /**
     * 如果分别设置 则联表更新
     * @param params
     */
    void updateParkMonthlyCarByTempCar(Map<String,Object> params);


}
