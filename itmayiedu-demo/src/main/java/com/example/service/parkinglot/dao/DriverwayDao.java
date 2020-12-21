package com.example.service.parkinglot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface DriverwayDao {

    /**
     * 查询停车场所有出入口列表
     * @param params
     * @return
     */
    List<Map<String,Object>> listDriverways(Map<String,String> params);

    /**
     * 查询停车场 出/入 口列表
     * @param params
     * @return
     */
    List<Map<String,Object>> listDriverwaysByType(Map<String,Object> params);

    /**
     * 查询停车场 已停用出入口 列表
     * @param params
     * @return
     */
    List<Map<String,Object>> listDriverwaysByStatus(Map<String,Object> params);

    /**
     * 修改停车出入口使用状态
     * @param params
     */
    void updateDriverwayStatus(Map<String,Object> params);
}
