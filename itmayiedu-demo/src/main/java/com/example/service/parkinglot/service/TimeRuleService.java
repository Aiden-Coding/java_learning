package com.example.service.parkinglot.service;

import com.example.service.parkinglot.dao.TimeRuleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TimeRuleService {
    @Autowired
    private TimeRuleDao timeRuleDao;

    /**
     * 分时包月规则获取
     * @param params
     * @return
     */

    public List<Map<String,Object>> listTimeRule(Map<String,String> params) {
        return timeRuleDao.listTimeRule(params);
    }
    /**
     * 分时包月规则添加
     * @param params
     * @return
     */
    public void saveTimeRule(Map<String,String> params){
        timeRuleDao.saveTimeRule(params);
    }
    /**
     * 分时包月规则时段详情
     * @param params
     * @return
     */
    public Map<String,Object> getTimeRule(Map<String,Object> params){
        return timeRuleDao.getTimeRule(params);
    }

    /**
     * 分时包月规则修改
     * @param params
     * @return
     */
    public void updateTimeRule(Map<String, String> params) {
        timeRuleDao.updateTimeRule(params);
    }
    /**
     * 分时包月规则删除
     * @param params
     * @return
     */
    public void deleteTimeRule(Map<String, String> params) {
        timeRuleDao.deleteTimeRule(params);
    }
    /**
     * 分时包月规则名称获取
     * @param params
     * @return
     */
    public List<Map<String,Object>> listTimeRuleName(Map<String,String> params) {
        return timeRuleDao.listTimeRuleName(params);
    }

}
