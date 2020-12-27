package com.itmayiedu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itmayeidu.service.LogService;
import com.itmayiedu.dao.LogDao;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;

	@Transactional(propagation = Propagation.MANDATORY)
	public void addLog() {
		logDao.add("log_" + System.currentTimeMillis());
	}

}
