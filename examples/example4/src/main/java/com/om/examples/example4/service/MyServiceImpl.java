package com.om.examples.example4.service;

import javax.inject.Inject;

import org.om.dao.annotation.transactional.Transactional;

import com.om.examples.example4.dao.MyPojoDAO;

/**
 * @author tome
 */
public class MyServiceImpl implements MyService {
   @Inject()
   private MyPojoDAO mypojodao;

   public MyPojoDAO getMypojodao() {
      return mypojodao;
   }

   public void setMypojodao(MyPojoDAO mypojodao) {
      this.mypojodao = mypojodao;
   }

   @Transactional()
   public void doStuff() {
      System.out.println("hi");
   }
}
