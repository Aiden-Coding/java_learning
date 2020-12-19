package com.example.service.parkinglot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ParkingMonthlyExtendDao {
    /**
     * 包月统计续费明细
     * @param params
     * @return
     */
    List<Map<String, Object>> getParkIncomeDetail(Map<String, Object> params);
}
