package com.example.service.parkinglot.service;

import com.example.service.parkinglot.dao.ParkingMonthlyExtendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParkMonthlyExthendService {
    @Autowired
    private ParkingMonthlyExtendDao extendDao;
    /**
     * 包月统计续费明细
     * @param params
     * @return
     */
    public List<Map<String, Object>> getParkIncomeDetail(Map<String, Object> params) {

        return extendDao.getParkIncomeDetail(params);

    }

}
