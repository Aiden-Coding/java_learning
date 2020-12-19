package com.example.service.parkinglot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.service.parkinglot.service.ParkMonthlyCarService;
import com.example.service.parkinglot.util.DatabaseUtils;
import com.example.service.parkinglot.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ParkMonthlyCarController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ParkMonthlyCarService parkMonthlyCarService;


    /**
     * 查询包月车辆列表 信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/listParkMonthlyCar",method = RequestMethod.POST)
    public Result listParkMonthlyCar(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyCarController: listParkMonthlyCar is accessed.");

        //获取总条数
        int totalRows=parkMonthlyCarService.getCountParkMonthlyCar(params);

        //计算当前页面
        int currentPage=Integer.parseInt(params.get("currentPage").toString());
        int pageSize= Integer.parseInt(params.get("pageSize").toString());
        int startRow = (currentPage - 1) * pageSize;

        //添加分页参数
        params.put("startRow",startRow);
        params.put("pageSize",pageSize);

        //查询包月车辆信息列表
        List<Map<String,Object>> list=parkMonthlyCarService.listParkMonthlyCar(params);

        return Result.buildResult(Result.Status.OK,"Ok",totalRows,list);
    }

    /**
     * 包月车辆占用情况
     * @param params
     * @return
     */
    @RequestMapping(value = "/listParkMonthlyCarInfo",method = RequestMethod.POST)
    public Result listParkMonthlyCarInfo(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyCarController: listParkMonthlyCarInfo is accessed.");

        //查询包月车辆信息列表
        List<Map<String,Object>> list=parkMonthlyCarService.listParkMonthlyCarInfo(params);

        return Result.buildResult(Result.Status.OK,"Ok",list);
    }

    /**
     * 添加一个包月汽车信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveParkMonthlyCar",method =RequestMethod.POST)
    public Result saveParkMonthlyCar(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyCarController: saveParkMonthlyCar is accessed.");

        //创建唯一主键
        String monthlyCarId= DatabaseUtils.generateUniqueKey();

        //添加参数
        params.put("monthlyCarId",monthlyCarId);

        //执行添加方法
        parkMonthlyCarService.saveParkMonthlyCar(params);

        return Result.buildResult(Result.Status.OK,"OK");
    }


    /**
     * 修改当前包月车辆信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateParkMonthlyCarInfo",method = RequestMethod.POST)
    public Result updateParkMonthlyCarInfo(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyCarController: updateParkMonthlyCarInfo is accessed.");

        //查询包月车辆信息列表
        parkMonthlyCarService.updateParkMonthlyCarInfo(params);

        return Result.buildResult(Result.Status.OK,"Ok");
    }

    /**
     * 删除一个包月车辆信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/deleteParkMonthlyCar",method = RequestMethod.POST)
    public Result deleteParkMonthlyCar(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyCarController: deleteParkMonthlyCar is accessed.");

        //查询包月车辆信息列表
        parkMonthlyCarService.deleteParkMonthlyCar(params);

        return Result.buildResult(Result.Status.OK,"Ok");
    }
}
