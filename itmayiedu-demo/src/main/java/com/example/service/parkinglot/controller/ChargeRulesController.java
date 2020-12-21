package com.example.service.parkinglot.controller;

import com.example.service.parkinglot.service.ChargeRulesService;
import com.example.service.parkinglot.util.DatabaseUtils;
import com.example.service.parkinglot.util.Result;
import org.apache.commons.lang.StringUtils;
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
import java.util.UUID;

@RestController
public class ChargeRulesController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChargeRulesService ChargeRulesService;

    /**
     * 根据停车场ID查询对应收费规则
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/listChargeRules", method = RequestMethod.POST)
    public Result listChargeRulesByParkinglotId(@RequestParam Map<String, String> params) {

        logger.info("ChargeRulesController: listChargeRulesByParkinglotId is accessed.");

        String parkinglotId = params.get("parkinglotId");

        //非空验证
        if (StringUtils.isEmpty(parkinglotId)) {
            logger.info("WebLoginController : parkinglotId is empty , return BAD_REQUEST.");
            return Result.buildResult(Result.Status.BAD_REQUEST, "parkinglotId is empty", null);
        }
        //执行
        List<Map<String, Object>> listChargeRules = ChargeRulesService.listChargeRulesByParkinglotId(parkinglotId);
        //结果
        return Result.buildResult(Result.Status.OK, "OK", listChargeRules);
    }


    /**
     * 根据收费规则ID查询收费规则
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/getChargeRule", method = RequestMethod.POST)
    public Result getChargeRule(@RequestParam Map<String, String> params) {
        logger.info("ChargeRulesController: getChargeRule is accessed.");
        String rulesId = params.get("rulesId");
        //非空验证
        if (StringUtils.isEmpty(rulesId)) {
            logger.info("WebLoginController : rulesId is empty , return BAD_REQUEST.");
            return Result.buildResult(Result.Status.BAD_REQUEST, "rulesId is empty", null);
        }
        //执行
        Map<String, Object> ChargeRule = ChargeRulesService.getChargeRule(rulesId);
        //结果
        return Result.buildResult(Result.Status.OK, "OK", ChargeRule);
    }

    /**
     * 根据当前用户 添加收费规则
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveChargeRule", method = RequestMethod.POST)
    public Result saveChargeRule(@RequestParam Map<String, String> params) {
        logger.info("ChargeRulesController: saveChargeRule is accessed.");
        //获取参数
        String rulesId = DatabaseUtils.generateUniqueKey();
        String parkinglotId = params.get("parkinglotId");
        String parkingType = params.get("parkingType");
        String licenseColor = params.get("licenseColor");
        String carType = params.get("carType");
        String valuationType = params.get("valuationType");
        String timeType = params.get("timeType");
        String freeTime = params.get("freeTime");
        String firstPrice = params.get("firstPrice");
        String firstTime = params.get("firstTime");
        String afterPrice = params.get("afterPrice");
        String afterTime = params.get("afterTime");
        String dayStartTime = params.get("dayStartTime");
        String dayEndTime = params.get("dayEndTime");
        String dayFirstPrice = params.get("dayFirstPrice");
        String dayFirstTime = params.get("dayFirstTime");
        String dayAfterPrice = params.get("dayAfterPrice");
        String dayAfterTime = params.get("dayAfterTime");
        String nightStartTime = params.get("nightStartTime");
        String nightEndTime = params.get("nightEndTime");
        String nightFirstPrice = params.get("nightFirstPrice");
        String nightFirstTime = params.get("nightFirstTime");
        String nightAfterPrice = params.get("nightAfterPrice");
        String nightAfterTime = params.get("nightAfterTime");
        String limitHour = params.get("limitHour");
        String limitPrice = params.get("limitPrice");
        String createBy = params.get("uid");

        //获取下一个顺序号
        Integer orderLevel = ChargeRulesService.getNextOrderLevel(parkinglotId);

        //装配参数
        Map<String, Object> chargeRules = new HashMap<String, Object>();
        chargeRules.put("rulesId", rulesId);
        chargeRules.put("parkinglotId", parkinglotId);
        chargeRules.put("parkinglotType", parkingType);
        chargeRules.put("licenseColor", licenseColor);
        chargeRules.put("carType", carType);
        chargeRules.put("valuationType", valuationType);
        chargeRules.put("timeType", timeType);
        chargeRules.put("freeTime", freeTime);
        chargeRules.put("firstPrice", firstPrice);
        chargeRules.put("firstTime", firstTime);
        chargeRules.put("afterPrice", afterPrice);
        chargeRules.put("afterTime", afterTime);
        chargeRules.put("dayStartTime", dayStartTime);
        chargeRules.put("dayEndTime", dayEndTime);
        chargeRules.put("dayFirstPrice", dayFirstPrice);
        chargeRules.put("dayFirstTime", dayFirstTime);
        chargeRules.put("dayAfterPrice", dayAfterPrice);
        chargeRules.put("dayAfterTime", dayAfterTime);
        chargeRules.put("nightStartTime", nightStartTime);
        chargeRules.put("nightEndTime", nightEndTime);
        chargeRules.put("nightFirstPrice", nightFirstPrice);
        chargeRules.put("nightFirstTime", nightFirstTime);
        chargeRules.put("nightAfterPrice", nightAfterPrice);
        chargeRules.put("nightAfterTime", nightAfterTime);
        chargeRules.put("limitHour", limitHour);
        chargeRules.put("limitPrice", limitPrice);
        chargeRules.put("createBy", createBy);
        chargeRules.put("orderLevel", orderLevel);
        //执行添加
        ChargeRulesService.saveChargeRule(chargeRules);
        //结果
        return Result.buildResult(Result.Status.OK, "Ok");
    }


    /**
     * 根据当收费规则 更改收费规则
     * @param params
     */
    @RequestMapping(value = "/updateChargeRule", method = RequestMethod.POST)
    public Result updateChargeRule(@RequestParam Map<String, String> params) {
        logger.info("ChargeRulesController: updateChargeRule is accessed.");
        //获取参数
        String rulesId = params.get("rulesId");
        String parkinglotId = params.get("parkinglotId");
        String parkinglotType = params.get("parkinglotType");
        String licenseColor = params.get("licenseColor");
        String carType = params.get("carType");
        String valuationType = params.get("valuationType");
        String timeType = params.get("timeType");
        String freeTime = params.get("freeTime");
        String firstPrice = params.get("firstPrice");
        String firstTime = params.get("firstTime");
        String dayStartTime = params.get("dayStartTime");
        String dayEndTime = params.get("dayEndTime");
        String dayFirstPrice = params.get("dayFirstPrice");
        String dayFirstTime = params.get("dayFirstTime");
        String dayAfterPrice = params.get("dayAfterPrice");
        String dayAfterTime = params.get("dayAfterTime");
        String nightStartTime = params.get("nightStartTime");
        String nightEndTime = params.get("nightEndTime");
        String nightFirstPrice = params.get("nightFirstPrice");
        String nightFirstTime = params.get("nightFirstTime");
        String nightAfterPrice = params.get("nightAfterPrice");
        String nightAfterTime = params.get("nightAfterTime");
        String limitHour = params.get("limitHour");
        String limitPrice = params.get("limitPrice");
        String updateBy = params.get("uid");

        //装配参数
        Map<String, Object> ChargeRules = new HashMap<String, Object>();
        ChargeRules.put("rulesId", rulesId);
        ChargeRules.put("parkinglotId", parkinglotId);
        ChargeRules.put("parkinglotType", parkinglotType);
        ChargeRules.put("licenseColor", licenseColor);
        ChargeRules.put("carType", carType);
        ChargeRules.put("valuationType", valuationType);
        ChargeRules.put("timeType", timeType);
        ChargeRules.put("freeTime", freeTime);
        ChargeRules.put("firstPrice", firstPrice);
        ChargeRules.put("firstTime", firstTime);
        ChargeRules.put("dayStartTime", dayStartTime);
        ChargeRules.put("dayEndTime", dayEndTime);
        ChargeRules.put("dayFirstPrice", dayFirstPrice);
        ChargeRules.put("dayFirstTime", dayFirstTime);
        ChargeRules.put("dayAfterPrice", dayAfterPrice);
        ChargeRules.put("dayAfterTime", dayAfterTime);
        ChargeRules.put("nightStartTime", nightStartTime);
        ChargeRules.put("nightEndTime", nightEndTime);
        ChargeRules.put("nightFirstPrice", nightFirstPrice);
        ChargeRules.put("nightFirstTime", nightFirstTime);
        ChargeRules.put("nightAfterPrice", nightAfterPrice);
        ChargeRules.put("nightAfterTime", nightAfterTime);
        ChargeRules.put("limitHour", limitHour);
        ChargeRules.put("limitPrice", limitPrice);
        ChargeRules.put("updateBy", updateBy);
        //执行更新
        ChargeRulesService.updateChargeRule(ChargeRules);
        //返回
        return Result.buildResult(Result.Status.OK, "OK");
    }

    /**
     * 根据收费规则ID删除 （更新删除标记）
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/deleteChargeRule", method = RequestMethod.POST)
    public Result deleteChargeRule(@RequestParam Map<String, String> params) {

        logger.info("ChargeRulesController: deleteChargeRule is accessed.");

        String rulesId = params.get("rulesId");
        String uid = params.get("uid");

        //非空验证
        if (StringUtils.isEmpty(rulesId)) {
            logger.info("WebLoginController : rulesId is empty , return BAD_REQUEST.");
            return Result.buildResult(Result.Status.BAD_REQUEST, "rulesId is empty", null);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rulesId", rulesId);
        map.put("uid", uid);

        //执行
        ChargeRulesService.deleteChargeRule(map);
        return Result.buildResult(Result.Status.OK, "OK");
    }


    /**
     * 修改计费规则 顺序
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/updateChargeRuleOrderLevel", method = RequestMethod.POST)
    public Result updateChargeRuleOrderLevel(@RequestParam Map<String, String> params) {
        logger.info("ChargeRulesController: updateChargeRuleOrderLevel is accessed.");

        //获取前端 当前参数
        String rulesId = params.get("rulesId");
        String parkinglotId = params.get("parkinglotId");
        String direction = params.get("direction");

        //非空验证
        if (StringUtils.isEmpty(rulesId)) {
            logger.info("WebLoginController : rulesId is empty , return BAD_REQUEST.");
            return Result.buildResult(Result.Status.BAD_REQUEST, "rulesId is empty", null);
        }
        //非空验证
        if (StringUtils.isEmpty(parkinglotId)) {
            logger.info("WebLoginController : parkinglotId is empty , return BAD_REQUEST.");
            return Result.buildResult(Result.Status.BAD_REQUEST, "parkinglotId is empty", null);
        }

        //获得当前被操作的计费规则
        Map<String, Object> currentChargeRule = ChargeRulesService.getChargeRule(rulesId);
        if (currentChargeRule == null) {
            logger.info("ChargeRulesController: updateChargeRuleOrderLevel, data[" + rulesId + "] is not exists.");
            return Result.buildResult(Result.Status.NOT_FOUND, "data is not exists.");
        }

        //记录当前计费规则顺序
        String currentLevel = currentChargeRule.get("orderLevel").toString();
        //上移
        if ("0".equals(direction)) {
            //获取被操作对象的上一条相邻数据
            Map<String, Object> less = ChargeRulesService.getLessOrderLevel(params);
            if (less == null) {
                logger.info("ChargeRulesController: updateChargeRuleOrderLevel, data[" + rulesId + "] is in the top.");
                return Result.buildResult(Result.Status.SERVICE_UNAVAILABLE, "data is in the top.");
            }
            String lessOrderLevel = less.get("orderLevel").toString();
            //将当前记录的顺序号更新为上一条的顺序号
            currentChargeRule.replace("orderLevel", lessOrderLevel);
            //更新相邻数据
            ChargeRulesService.updateOrderLevel(currentChargeRule);
            //将上一条记录的顺序号更新为当前的顺序号
            less.replace("orderLevel", currentLevel);
            //更新相邻数据
            ChargeRulesService.updateOrderLevel(less);
        }

        //下移
        if ("1".equals(direction)) {
            //获取被操作对象的下一条相邻数据
            Map<String, Object> greater = ChargeRulesService.getGreaterOrderLevel(params);
            if (greater == null) {
                logger.info("ChargeRulesController: updateChargeRuleOrderLevel, data[" + rulesId + "] is in the end.");
                return Result.buildResult(Result.Status.SERVICE_UNAVAILABLE, "data is in the end.");
            }
            String greaterOrderLevel = greater.get("orderLevel").toString();
            //将当前记录的顺序号更新为下一条的顺序号
            currentChargeRule.replace("orderLevel", greaterOrderLevel);
            //更新相邻数据
            ChargeRulesService.updateOrderLevel(currentChargeRule);
            //将下一条记录的顺序号更新为当前的顺序号
            greater.replace("orderLevel", currentLevel);
            //更新相邻数据
            ChargeRulesService.updateOrderLevel(greater);
        }
        return Result.buildResult(Result.Status.OK, "OK");
    }

}
