
package com.itmayiedu02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class TransactionUtils {
	@Autowired
	private DataSourceTransactionManager dataSourceTransactionManager;

	public TransactionStatus begin() {
		TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
		return transaction;
	}

	public void commit(TransactionStatus transaction) {
		dataSourceTransactionManager.commit(transaction);
	}

	public void rollback(TransactionStatus transaction) {
		dataSourceTransactionManager.rollback(transaction);
	}

}
