package com.om.examples.example4.service;

import javax.inject.Inject;

import org.om.dao.annotation.transactional.Transactional;
import org.om.dao.genericdao.GenericDAO;

import com.khubla.simpleioc.annotation.RegistryBean;
import com.om.examples.example4.pojo.MyPojo;

@RegistryBean(name = "myservice")
public class MyServiceImpl implements MyService {

	@Inject()
	private GenericDAO<MyPojo> mypojodao;

	public GenericDAO<MyPojo> getMypojodao() {
		return mypojodao;
	}

	public void setMypojodao(GenericDAO<MyPojo> mypojodao) {
		this.mypojodao = mypojodao;
	}

	@Transactional(jcr = "jcrpersistencecontext")
	public void doStuff() {
		System.out.println("hi");
	}

}
