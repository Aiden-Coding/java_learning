package com.mayikt.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.zaxxer.hikari.HikariDataSource;

import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "sharding.jdbc")
public class ShardingMasterSlaveConfig {

	// 存放本地多个数据源
	private Map<String, HikariDataSource> dataSources = new HashMap<>();

	private MasterSlaveRuleConfiguration masterSlaveRule;
}
