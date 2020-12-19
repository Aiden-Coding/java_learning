package com.example.service.parkinglot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SpecialCarDao {

    /**
     * 查询特殊车辆列表根据specialType
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> listSpecialCar(Map<String, Object> params);

    /**
     * 通过Id查询特殊车辆总数用于分页
     *
     * @param params
     * @return
     */
    int getCountSpecialCarLot(Map<String, Object> params);

    /**
     * 删除特殊车辆（修改状态）
     * @param params carID
     */
    void deleteSpecialCar(Map<String, String> params);

    /**
     * 编辑特殊车辆
     *
     * @param params 参数列表
     */
    void updateSpecialCar(Map<String, String> params);


    /**
     * 添加特殊车辆
     *
     * @param params 参数列表
     */

    void saveSpecialCar(Map<String, Object> params);

}
