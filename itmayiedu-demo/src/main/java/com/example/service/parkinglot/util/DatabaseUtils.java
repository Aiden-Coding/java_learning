package com.example.service.parkinglot.util;

import java.util.Random;
import java.util.UUID;

/**
 *
 * <p> Title: 数据库操作辅助类</p>
 *
 * <p> Description: 数据库辅助静态方法类</p>
 *
 * <p> Copyright: Copyright (c) 2017 by unknown </p>
 *
 * <p> Company: yu feng </p>
 *
 * @author: unknown
 * @version: 1.0
 * @date: 2017年11月30日 下午17:40:27
 *
 */
public class DatabaseUtils {

    public static String generateUniqueKey(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}


