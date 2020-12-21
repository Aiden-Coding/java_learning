package com.example.service.parkinglot.controller;

import com.example.service.parkinglot.service.DriverwayService;
import com.example.service.parkinglot.util.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class DriverwayController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DriverwayService driverwayService;

    @RequestMapping(value = "/listDriverways", method = RequestMethod.POST)
    public Result listDriverways(@RequestParam Map<String, Object> params) {

        logger.info("DriverwayController: listDriverways is accessed.");

        //获取参数
        String unitId = params.get("unitId").toString();
        String drivewayName = params.get("drivewayName").toString();

        //判断参数
        if (StringUtils.isEmpty(unitId)) {
            logger.info("WebLoginController : unitId is empty , return BAD_REQUEST.");
            return Result.buildResult(Result.Status.BAD_REQUEST, "unitId is empty", null);
        }
        //装配参数列表
        Map<String, String> map = new HashMap<String, String>();
        map.put("unitId", unitId);
        map.put("drivewayName", drivewayName);
        //执行查询方法
        List<Map<String, Object>> list = driverwayService.listDriverways(map);

        return Result.buildResult(Result.Status.OK, "OK", list);
    }

    /**
     * 更改停车场道闸状态
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateDriverwayStatus", method = RequestMethod.POST)
    public Result updateDriverwayStatus(@RequestParam Map<String, Object> params) {

        logger.info("DriverwayController: updateDriverwayStatus is accessed.");

        driverwayService.updateDriverwayStatus(params);

        return Result.buildResult(Result.Status.OK, "OK");
    }


    /**
     * 查询停车场 出入口 列表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/listDriverwaysByType", method = RequestMethod.POST)
    public Result listDriverwaysByType(@RequestParam Map<String, Object> params) {

        logger.info("DriverwayController: listDriverwaysByType is accessed.");

        List<Map<String, Object>> list = driverwayService.listDriverwaysByType(params);
        return Result.buildResult(Result.Status.OK, "OK", list);

    }

    /**
     * 查询停车场已停用列表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/listDriverwaysByStatus", method = RequestMethod.POST)
    public Result listDriverwaysByStatus(@RequestParam Map<String, Object> params) {

        logger.info("DriverwayController: listDriverwaysByStatus is accessed.");

        List<Map<String, Object>> list = driverwayService.listDriverwaysByStatus(params);

        return Result.buildResult(Result.Status.OK, "OK", list);
    }

}
