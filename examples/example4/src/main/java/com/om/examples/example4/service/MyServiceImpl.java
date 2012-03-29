package com.om.examples.example4.service;

import org.om.dao.annotation.transactional.Transactional;
import org.om.dao.util.SessionUtil;

import com.om.examples.example4.dao.MyPojoDAO;

public class MyServiceImpl implements MyService {
	@Transactional(jcr = "jcrpersistencecontext")
	public void doStuff() {
		SessionUtil.getSession();
		new MyPojoDAO();
	}

}
