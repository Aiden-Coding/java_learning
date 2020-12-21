package com.example.service.parkinglot.controller;

import com.example.service.parkinglot.service.TimeRuleService;
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
public class TimeRuleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TimeRuleService timeRuleService;

    /**
     * 分时包月规则获取
     * @param params
     * @return
     */
    @RequestMapping(value = "/listTimeRule",method = RequestMethod.POST)
    public Result listTimeRule(@RequestParam Map<String,String> params) {

        logger.info("TimeRuleController: listTimeRule is accessed.");
        List<Map<String,Object>> list = timeRuleService.listTimeRule(params);

        return Result.buildResult(Result.Status.OK,"OK",list);
    }
    /**
     * 分时包月规则名称获取
     * @param params
     * @return
     */
    @RequestMapping(value = "/listTimeRuleName",method = RequestMethod.POST)
    public Result listTimeRuleName(@RequestParam Map<String,String> params) {

        logger.info("TimeRuleController: listTimeRuleName is accessed.");

        List<Map<String,Object>> list = timeRuleService.listTimeRuleName(params);

        return Result.buildResult(Result.Status.OK,"OK",list);
    }

    /**
     * 时段详情
     * @param params
     * @return
     */
    @RequestMapping(value = "/getTimeRule",method = RequestMethod.POST)
    public Result getTimeRule(@RequestParam Map<String,Object> params) {

        logger.info("TimeRuleController: getTimeRule is accessed.");
        params=timeRuleService.getTimeRule(params);

        return Result.buildResult(Result.Status.OK,"OK",params);
    }






    /**
     * 分时包月规则添加
     * @param params
     * @return
     */
    @RequestMapping(value = "/saveTimeRule",method = RequestMethod.POST)

    public Result saveTimeRule(@RequestParam Map<String,String> params) {

        logger.info("TimeRuleController: saveTimeRule is accessed.");
        if (params!=null && !params.isEmpty()) {
            String timeRuleId = DatabaseUtils.generateUniqueKey();
            params.put("timeRuleId", timeRuleId);
            timeRuleService.saveTimeRule(params);
        }
        return Result.buildResult(Result.Status.OK,"OK");
    }


    /**
     * 分时包月规则修改
     * @param params
     * @return
     */

    @RequestMapping(value = "/updateTimeRule",method = RequestMethod.POST)

    public Result updateTimeRule(@RequestParam Map<String,String> params) {

        logger.info("TimeRuleController: updateTimeRule is accessed.");
        if (params!=null && !params.isEmpty()) {
            timeRuleService.updateTimeRule(params);
        }
        return Result.buildResult(Result.Status.OK,"OK");
    }


    /**
     * 分时包月规则删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/deleteTimeRule",method = RequestMethod.POST)

    public Result deleteTimeRule(@RequestParam Map<String,String> params) {

        logger.info("TimeRuleController: deleteTimeRule is accessed.");
        if (params!=null && !params.isEmpty()) {
            timeRuleService.deleteTimeRule(params);
        }
        return Result.buildResult(Result.Status.OK,"OK");
    }
}
