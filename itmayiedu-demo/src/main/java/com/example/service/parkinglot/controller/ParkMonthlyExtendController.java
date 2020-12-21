package com.example.service.parkinglot.controller;

import com.alibaba.fastjson.JSON;
import com.example.service.parkinglot.feign.MsFinanceApi;
import com.example.service.parkinglot.service.ParkMonthlyExtendService;
import com.example.service.parkinglot.service.ParkinglotService;
import com.example.service.parkinglot.util.DatabaseUtils;
import com.example.service.parkinglot.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParkMonthlyExtendController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ParkMonthlyExtendService parkMonthlyExtendService;

    @Autowired
    private MsFinanceApi msFinanceApi;

    @Autowired
    private ParkinglotService parkinglotService;



    @RequestMapping(value = "/getAmountMonthly",method = RequestMethod.POST)
    public Result getAmountMonthly(@RequestParam Map<String,Object> params){
        int sum=parkMonthlyExtendService.getCountMonthlyBySum(params);
        int general=parkMonthlyExtendService.getCountMonthlyByGeneral(params);
        int subsection=parkMonthlyExtendService.getCountMonthlyBySubsection(params);
        int soondue=parkMonthlyExtendService.getCountMonthlyBySoondue(params);
        int overdue=parkMonthlyExtendService.getCountMonthlyByOverdue(params);

        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("sum",sum);
        map.put("general",general);
        map.put("subsection",subsection);
        map.put("soondue",soondue);
        map.put("overdue",overdue);

        return Result.buildResult(Result.Status.OK,"OK",map);
    }

    /**
     * 初始化续费用户信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/getMonthlyInfo",method = RequestMethod.POST)
    public Result getMonthlyInfo(@RequestParam Map<String,Object> params){
        logger.info("ParkMonthlyController: listNearEndDate is accessed.");

        Map<String,Object> info=parkMonthlyExtendService.getMonthlyInfo(params);

        return  Result.buildResult(Result.Status.OK,"OK",info);
    }

    /**
     * 初始化续费用户信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/exportMonthlyCarInfo",method = RequestMethod.POST)
    public Result exportMonthlyCarInfo(@RequestParam Map<String,Object> params){
        logger.info("ParkMonthlyController: exportMonthlyCarInfo is accessed.");

        List<Map<String,Object>> info=parkMonthlyExtendService.exportMonthlyCarInfo(params);

        return  Result.buildResult(Result.Status.OK,"OK",info);
    }

    /**
     * 保存数据到临时表
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveMonthlyCarInfo",method = RequestMethod.POST)
    public Result saveMonthlyCarInfo(@RequestParam Map<String,Object> params){

        logger.info("ParkMonthlyController: saveMonthlyCarInfo is accessed.");

        //清空临时表数据
        parkMonthlyExtendService.deleteMonthlyCarTemp(params);

        String guid=params.get("guid").toString();
        //接收参数为"carInfoList"
        Object obj=params.get("carInfoList");

        List<Map> map= JSON.parseArray(obj.toString(),Map.class);
        for (Map<String,Object> o: map){
            o.put("guid",guid);
            parkMonthlyExtendService.saveMonthlyCarInfo(o);
        }
        return  Result.buildResult(Result.Status.OK,"OK");
    }

    /**
     * 获取 最近三个到期日期
     * @param params
     * @return
     */
    @RequestMapping(value = "/listNearEndDate",method = RequestMethod.POST)
    public Result listNearEndDate(@RequestParam Map<String,Object> params){
        logger.info("ParkMonthlyController: listNearEndDate is accessed.");

        List<Map<String,Object>> info=parkMonthlyExtendService.listNearEndDate(params);

        return  Result.buildResult(Result.Status.OK,"OK",info);
    }

    /**
     * 初始化 去设置页面
     * @param params
     * @return
     */
    @RequestMapping(value = "/getMonthlyCarInit",method = RequestMethod.POST)
    public Result getMonthlyCarInit(@RequestParam Map<String,Object> params){
        logger.info("ParkMonthlyController: listNearEndDate is accessed.");

        List<Map<String,Object>> info=parkMonthlyExtendService.getMonthlyCarInit(params);

        return  Result.buildResult(Result.Status.OK,"OK",info);
    }

//    /**
//     * 更新 去设置页面
//     * @param params
//     * @return
//     */
//    @RequestMapping(value = "/updateMonthlyCarInfo",method = RequestMethod.POST)
//    public Result updateMonthlyCarInfo(@RequestParam Map<String,Object> params){
//        logger.info("ParkMonthlyController: listNearEndDate is accessed.");
//
//        parkMonthlyExtendService.updateMonthlyCarInfo(params);
//
//        return  Result.buildResult(Result.Status.OK,"OK");
//    }

    /**
     * 添加续费记录
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveParkMonthlyExtends",method = RequestMethod.POST)
    public Result saveParkMonthlyExtends(@RequestParam Map<String,Object> params){
        logger.info("ParkMonthlyController: saveParkMonthlyExtends is accessed.");

        String extendId= DatabaseUtils.generateUniqueKey();
        params.put("paymentType",1);
        params.put("payTypecode",1001);

//        //判断更新 主表 包月车辆信息 到期日期
//        if ("0".equals(params.get("expdateType").toString())){
//
//            parkMonthlyExtendService.updateParkMonthlyCarUnified(params);
//        }
//        if ("1".equals(params.get("expdateType").toString())){
//
//            parkMonthlyExtendService.updateParkMonthlyCarByTempCar(params);
//        }
//        //添加续费记录
//        params.put("extendId",extendId);
//        parkMonthlyExtendService.saveParkMonthlyExtends(params);
//        //清空临时表
//        parkMonthlyExtendService.deleteMonthlyCarTemp(params);

        /**
         * 调用微服务  财务系统
         */
        logger.info("ParkMonthlyController: saveTemporaryParkingDetail is accessed.");

//        //获取停车场基本信息
//        Map<String,Object> map=parkinglotService.getParkinglot(params.get("parkingLotId").toString());
//        String brandId = map.get("brandId").toString();
//        String agentId =map.get("agentId").toString();
//        String estateId=map.get("estateId").toString();

        //添加参数列表
//        params.put("brandId",brandId);
//        params.put("agentId",agentId);
//        params.put("estateId",estateId);

        params.put("incomeName",params.get("ownerName"));//包月用户姓名
        params.put("dataId",extendId);//续费记录ID
        params.put("amountReceivable",params.get("monthlyAmount"));//应收金额
        params.put("amountTotal",params.get("amountReceivable"));//消费总额
        params.put("amountDiscount","0");// TODO 消费抵用券 计算
        params.put("amountPayed",params.get("amountReceivable"));//实际金额
        params.put("payType",params.get("paymentType"));//付款方式
        params.put("payTypecode",params.get("payTypecode"));//付款方式编码
        params.put("incomeCategory",1);

        Map<String,Object> result =msFinanceApi.saveTemporaryParkingDetail(params);
        System.err.println(result.get("status"));

        if ("200".equals(result.get("status"))){
            return Result.buildResult(Result.Status.OK,"OK");
        }else {
            return Result.buildResult(Result.Status.SERVICE_UNAVAILABLE,"方法："+result.get("name")+"msFinanceApi 调用失败");
        }

    }


}
