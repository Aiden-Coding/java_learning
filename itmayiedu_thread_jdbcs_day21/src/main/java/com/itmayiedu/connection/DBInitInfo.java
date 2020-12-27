package com.itmayiedu.connection;

import java.util.ArrayList;
import java.util.List;

import com.itmayiedu.DbBean;

/**
 * 模拟初始化配置文件<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
public class DBInitInfo {

	public static List<DbBean> listDbBeans;

	static {
		listDbBeans = new ArrayList<DbBean>();
		DbBean beanOracle = new DbBean();
		beanOracle.setDriverName("xxx");
		beanOracle.setUrl("xxx");
		beanOracle.setUserName("xxx");
		beanOracle.setPassword("xxx");

		beanOracle.setMinConnections(5);
		beanOracle.setMaxConnections(100);

		beanOracle.setPoolName("testPool");
		listDbBeans.add(beanOracle);
	}

}
