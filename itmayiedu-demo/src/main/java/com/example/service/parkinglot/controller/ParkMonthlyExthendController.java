package com.example.service.parkinglot.controller;

import com.example.service.parkinglot.ParkinglotApplication;
import com.example.service.parkinglot.service.ParkMonthlyExthendService;
import com.example.service.parkinglot.service.ParkinglotService;
import com.example.service.parkinglot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParkMonthlyExthendController {

    @Autowired
    private ParkMonthlyExthendService exthendService;
    @Autowired
    private ParkinglotService parkinglotService;
    /**
     * 包月统计续费明细
     * @param params
     * @return
     */
    @RequestMapping(value = "/getParkIncomeDetail", method = RequestMethod.POST)
    public Result getParkIncomeDetail(@RequestParam Map<String, Object> params) {

        int totalPage = parkinglotService.getCountparkinglot(params);
        //计算当前页面
        int currentPage = Integer.parseInt(params.get("currentPage").toString());
        int pageSize = Integer.parseInt(params.get("pageSize").toString());
        int startRow = (currentPage - 1) * pageSize;

        //封装参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRow", startRow);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> result = exthendService.getParkIncomeDetail(map);
        return Result.buildResult(Result.Status.OK, "OK", totalPage,result);
    }

}
