package com.example.service.parkinglot.service;

import com.example.service.parkinglot.dao.ChargeRulesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChargeRulesService {


    @Autowired
    private ChargeRulesDao chargeRulesDao;

    /**
     * 根据停车场ID查询对应 收费规则
     * @param parkinglotId
     * @return
     */
    public List<Map<String,Object>> listChargeRulesByParkinglotId(String parkinglotId){
        return chargeRulesDao.listChargeRulesByParkinglotId(parkinglotId);
    }

    /**
     * 根据收费规则ID查询 收费规则
     * @param rulesId
     * @return
     */
    public Map<String,Object> getChargeRule(String rulesId){

        return chargeRulesDao.getChargeRule(rulesId);
    }

    /**
     * 根据停车场ID添加收费规则
     * @param params
     */
    public void saveChargeRule(Map<String,Object> params){

        chargeRulesDao.saveChargeRule(params);
    }

    /**
     * 根据收费规则ID编辑收费规则
     * @param params
     */
    public  void  updateChargeRule(Map<String,Object> params){

        chargeRulesDao.updateChargeRule(params);
    }

    /**
     * 根据收费规则ID 删除收费规则（修改状态）
     * @param params
     */
    public void deleteChargeRule(Map<String,Object> params){
        chargeRulesDao.deleteChargeRule(params);
    }

    /**
     * 获取计费顺序最大值自动+1
     * @param parkinglotId
     * @return
     */
    public int getNextOrderLevel(String parkinglotId){
        return chargeRulesDao.getNextOrderLevel(parkinglotId);
    }

    /**
     * 获取 大于当前项的第一条数据
     * @param params
     * @return
     */
    public  Map<String,Object> getGreaterOrderLevel(Map<String,String> params){
        return chargeRulesDao.getGreaterOrderLevel(params);
    }


    /**
     * 获取 小于当前项的第一条数据
     * @param params
     * @return
     */
    public  Map<String,Object> getLessOrderLevel(Map<String,String> params){
        return chargeRulesDao.getLessOrderLevel(params);
    }

    /**
     * 修改计费顺序等级
     * @param params
     */
    public void updateOrderLevel(Map<String,Object> params){

        chargeRulesDao.updateOrderLevel(params);
    }
}
