package com.example.service.parkinglot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TimeRuleDao {
    /**
     * 查询分时包月规则据停车场名字
     * @param params
     * @return
     */
    List<Map<String,Object>> listTimeRule(Map<String,String> params);
    /**
     * 分时包月规则添加
     * @param params
     * @return
     */
    void saveTimeRule(Map<String,String> params);

    /**
     * 分时包月规则时段详情
     * @param params
     * @return
     */
    Map<String,Object> getTimeRule(Map<String,Object> params);

    /**
     * 分时包月规则修改
     * @param params
     * @return
     */

    void updateTimeRule(Map<String, String> params);
    /**
     * 分时包月规则删除
     * @param params
     * @return
     */
    void deleteTimeRule(Map<String, String> params);

    /**
     * 分时包月规则名称获取
     * @param params
     * @return
     */
    List<Map<String,Object>> listTimeRuleName(Map<String,String> params);

}
