package org.om.dao.annotation.transactional.filter;

import org.om.dao.annotation.transactional.Transactional;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * @author tome
 */
public class JCRTransactionalModule extends AbstractModule {
   @Override
   protected void configure() {
      bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class), new JCRTransactionalMethodIterceptor());
   }
}