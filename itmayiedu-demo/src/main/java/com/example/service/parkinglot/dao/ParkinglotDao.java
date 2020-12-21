package com.example.service.parkinglot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ParkinglotDao {


    /**
     * 通过平台ID查询 该平台管理的总停车场数
     *
     * @param params
     * @return
     */
    int getCountParkinglot(Map<String, Object> params);

    /**
     * 查询停车场列表
     *
     * @param params 参数列表
     */
    List<Map<String, Object>> listParkinglot(Map<String, Object> params);

    /**
     * 获取嵌套停车场下拉列表数据
     *
     * @param parkinglotId
     */
    List<Map<String, Object>> listNestParkinglotClasicInfo(String parkinglotId);

    /**
     * 通过ID查询车场信息
     *
     * @param parkinglotId
     * @return
     */
    Map<String, Object> getParkinglot(String parkinglotId);


    /**
     * 修改停车场参数（通用方法）
     *
     * @param params
     * @return
     */
    void updateParkinglotFlags(Map<String, Object> params);

    /**
     * 停车场统计营业总览
     *
     * @param params
     * @return
     */
    Map<String, Object> getParkLotCount(Map<String, Object> params);
    /**
     *查询商户充值所需停车场信息
     *
     * @param params
     * @return
     */
    Map<String, Object> getMerchantsParking(Map<String, Object> params);

}
