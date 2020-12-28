//package com.mayikt.config;
//
//import java.util.Collection;
//
//import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
//import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
//
//// 分库策略配置
//public class DatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {
//
//	// 考虑表的数据存放在那个库里面
//	@Override
//	public String doEqualSharding(Collection<String> databases, ShardingValue<Long> shardingValue) {
//		for (String databaseName : databases) {
//			if (databaseName.endsWith(shardingValue.getValue() % 2 + "")) {
//				return databaseName;
//			}
//		}
//		throw new IllegalArgumentException();
//	}
//
//	@Override
//	public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
//
//		return null;
//	}
//
//	@Override
//	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
//			ShardingValue<Long> shardingValue) {
//
//		return null;
//	}
//
//}
