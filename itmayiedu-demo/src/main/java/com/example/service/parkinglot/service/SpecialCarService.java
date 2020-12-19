package com.example.service.parkinglot.service;


import com.example.service.parkinglot.dao.SpecialCarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpecialCarService {
    @Autowired
    private SpecialCarDao specialCarDao;

    /**
     * 查询特殊车辆列表根据specialType
     * @param params
     * @return
     */
    public List<Map<String,Object>> listSpecialCar(Map<String,Object> params){

        return specialCarDao.listSpecialCar(params);
    }

    /**
     * 删除特殊车辆（修改状态）
     *  @param params carID
     *
     */
    public void deleteSpecialCar(Map<String,String> params) {

        specialCarDao.deleteSpecialCar(params);
    }

    /**
     * 通过Id查询特殊车辆总数用于分页
     * @param params
     * @return
     */
    public int getCountSpecialCarLot(Map<String, Object> params) {
        return specialCarDao.getCountSpecialCarLot(params);
    }
    /**
     * 编辑特殊车辆
     *  @param params 参数列表
     *
     */
    public  void updateSpecialCar(Map<String,String> params){
        specialCarDao.updateSpecialCar(params);
    }
    /**
     * 添加特殊车辆
     *  @param params 参数列表
     *
     */
    public  void saveSpecialCar(Map<String,Object> params){
        specialCarDao.saveSpecialCar(params);
    }

}
