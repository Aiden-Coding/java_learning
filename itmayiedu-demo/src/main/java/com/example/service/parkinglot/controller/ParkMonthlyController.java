package com.example.service.parkinglot.controller;

import com.alibaba.fastjson.JSON;
import com.example.service.parkinglot.service.ParkMonthlyService;
import com.example.service.parkinglot.util.DatabaseUtils;
import com.example.service.parkinglot.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ParkMonthlyController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ParkMonthlyService  parkMonthlyService;
    /**
     * 根据停车场ID查询包月用户信息（分页）
     * @param params(当前页)
     * @return
     */
    @RequestMapping(value = "/listParkMonthly",method = RequestMethod.POST)
    public Result listParkMonthly(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyController: listParkMonthly is accessed.");

        //获取总记录数
        int totalRows =parkMonthlyService.getCountParkMonthly(params);

        //计算当前页面
        int currentPage=Integer.parseInt(params.get("currentPage").toString());
        int pageSize= Integer.parseInt(params.get("pageSize").toString());
        int startRow = (currentPage - 1) * pageSize;

        //添加分页参数
        params.put("startRow",startRow);
        params.put("pageSize",pageSize);

        List<Map<String,Object>> result = parkMonthlyService.listParkMonthly(params);

        return  Result.buildResult(Result.Status.OK,"OK",totalRows ,result);
    }

    /**
     * 根据停车场查询条件，导出包月信息
     * @param params(当前页)
     * @return
     */
    @RequestMapping(value = "/exportParkMonthly",method = RequestMethod.POST)
    public Result exportParkMonthly(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyController: exportParkMonthly is accessed.");

        List<Map<String,Object>> result = parkMonthlyService.exportParkMonthly(params);

        return  Result.buildResult(Result.Status.OK,"OK" ,result);
    }

    /**
     * 保存包月基本信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveParkMonthly",method = RequestMethod.POST)
    public Result saveParkMonthly(@RequestParam Map<String,Object> params){

        String topic="saveParkMonthly";

        logger.info("ParkMonthlyController: saveParkMonthly is accessed.");

        //生成主键ID
        String parkMonthlyId = DatabaseUtils.generateUniqueKey();

        params.put("monthlyId",parkMonthlyId);

        //保存基本信息
        parkMonthlyService.saveParkMonthly(params);

        //TODO 写入消息队列进行数据同步

        //****返回主键ID
        return  Result.buildResult(Result.Status.OK,"OK" ,parkMonthlyId);
    }

    /**
     * 更新包月基本信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateParkMonthly",method = RequestMethod.POST)
    public Result updateParkMonthly(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyController: updateParkMonthly is accessed.");

        //保存基本信息
        parkMonthlyService.updateParkMonthly(params);

        //TODO 写入消息队列进行数据同步

        return  Result.buildResult(Result.Status.OK,"OK");
    }

    /**
     * 删除包月基本信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/deleteParkMonthly",method = RequestMethod.POST)
    public Result deleteParkMonthly(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyController: deleteParkMonthly is accessed.");

        //删除基本信息
        parkMonthlyService.deleteParkMonthly(params);

        //TODO 写入消息队列进行数据同步

        return  Result.buildResult(Result.Status.OK,"OK");
    }

    /**
     * 保存包月用户信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveParkMonthlyInfoAndOccupy",method = RequestMethod.POST)
    public Result saveParkMonthlyOccupy(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyController: saveParkMonthlyInfoAndOccupy is accessed.");

        System.out.println(params);
        //保存包月用户信息
        parkMonthlyService.updateParkMonthly(params);
        //获取包月车辆信息列表
        Object obj=params.get("occupylist");
        //转换list为JSON为字符串
        List<Map> map=JSON.parseArray(obj.toString(),Map.class);
            //获取每个对象
            for (Map<String,Object> o: map) {
                //判断是否初始化
                if (10>o.get("occupyId").toString().length() || null==o.get("occupyId").toString()){
                    String occupyId=DatabaseUtils.generateUniqueKey();
                    o.put("occupyId",occupyId);
                    //执行添加初始化信息
                    parkMonthlyService.saveParkMonthlyOccupy(o);
                }else {
                    //执行修改信息
                    parkMonthlyService.updateParkMonthlyOccupy(o);
                }
            }
        return  Result.buildResult(Result.Status.OK,"OK");
    }
}
