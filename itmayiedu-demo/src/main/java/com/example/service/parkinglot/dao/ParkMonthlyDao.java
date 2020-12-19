package com.example.service.parkinglot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ParkMonthlyDao {

    /**
     * 根据停车场查询包月名单列表
     * @param params 参数列表
     * @return
     */
    List<Map<String,Object>> listParkMonthly(Map<String,Object> params);

    /**
     * 根据停车场查询包月名单列表总数用于分页
     * @param params 参数列表
     * @return
     */
    int getCountParkMonthly(Map<String, Object> params);

    /**
     * 根据停车场查询导出包月名单列表
     * @param params 参数列表
     * @return
     */
    List<Map<String,Object>> exportParkMonthly(Map<String,Object> params);


    /**
     * 添加包月用户基本信息
     *  @param params 参数列表
     *
     */
    void saveParkMonthly(Map<String,Object> params);

    /**
     * 修改包月用户基本信息
     *  @param params 参数列表
     *
     */

    void updateParkMonthly(Map<String,Object> params);

    /**
     * 删除包月用户
     *  @param params 参数列表
     *
     */
    void deleteParkMonthly (Map<String,Object> params);

    /**
     * 保存包月车辆信息
     * @param params
     */
    void saveParkMonthlyOccupy(Map<String,Object> params);

    /**
     * 修改包月车辆信息
     * @param params
     */
    void updateParkMonthlyOccupy(Map<String,Object> params);











}
