
package com.itmayiedu02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class TransactionUtils {

	@Autowired
	private DataSourceTransactionManager dataSourceTransactionManagerl;
	
	//开启事物
	public TransactionStatus begin(){
		TransactionStatus transaction = dataSourceTransactionManagerl.getTransaction(new DefaultTransactionDefinition());
	    System.out.println("开启事物");
		return transaction;
	}
	//提交事物
   public void commit(TransactionStatus transaction){
	   dataSourceTransactionManagerl.commit(transaction);
	   System.out.println("提交事物");
   }
   //回滚事物
   public void rollback(TransactionStatus transaction){
	   dataSourceTransactionManagerl.rollback(transaction);
	   System.out.println("回滚事物");
   }
}
