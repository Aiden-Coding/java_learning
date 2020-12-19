package com.example.service.parkinglot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ChargeRulesDao {

    /**
     * 根据停车场ID，查询所有计费规则
     * @param parkinglotId 停车场ID
     */
    List<Map<String,Object>> listChargeRulesByParkinglotId(String parkinglotId);

    /**
     * 根据计费规则ID，查询计费规则
     * @param rulesId 计费规则ID
     */
    Map<String,Object> getChargeRule(String rulesId);

    /**
     * 根据停车场ID，添加计费规则
     * @param params 参数列表
     */
    void saveChargeRule(Map<String,Object> params);

    /**
     * 更新计费规则
     * @param params 参数列表
     */
    void updateChargeRule(Map<String,Object> params);

    /**
     * 删除计费规则
     * @param params 计费规则ID
     */
    void deleteChargeRule(Map<String,Object> params);

    /**
     * 计费顺序最大值+1
     * @param parkinglotId
     * @return
     */
    int getNextOrderLevel(String parkinglotId);

    /**
     * 获取 大于 当前项的第一条数据
     * @param params
     * @return
     */
    Map<String,Object> getGreaterOrderLevel(Map<String,String> params);

    /**
     * 获取 小于 当前项的第一条数据
     * @param params
     * @return
     */

    Map<String,Object> getLessOrderLevel(Map<String,String> params);


    /**
     * 更改
     * @param params
     */
    void updateOrderLevel(Map<String,Object> params);
}
