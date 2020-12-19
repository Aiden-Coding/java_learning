
package com.itmayiedu02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itmayiedu02.dao.LogDao;

@Service
public class LogService {
	@Autowired
	private LogDao logDao;

	@Transactional(propagation = Propagation.NEVER)
	public void addLog() {
		logDao.addLog();
		// int i = 1 / 0;
	}

}
