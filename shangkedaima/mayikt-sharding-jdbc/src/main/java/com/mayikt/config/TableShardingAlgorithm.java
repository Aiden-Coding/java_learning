//package com.mayikt.config;
//
//import java.util.Collection;
//
//import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
//import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
//
//public class TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {
//
//	// sql 中关键字 匹配符为 =的时候，表的路由函数
//	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
//		// availableTargetNames 分表的集合 t_order_0 和t_order_1
//		for (String tableName : availableTargetNames) {
//			// shardingValue.getValue() userid 分片字段值
//			// tableName = t_order_0 shardingValue.getValue()=2
//			// t_order_0 2%2=0
//			if (tableName.endsWith(shardingValue.getValue() % 2 + "")) {
//				return tableName;
//			}
//		}
//
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
