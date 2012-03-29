package org.om.dao.annotation.transactional.filter;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.om.core.impl.persistence.jcr.JcrPersistenceContext;
import org.om.dao.annotation.transactional.Transactional;

/**
 * 
 * @author tome
 * 
 */
public class JCRTransactionalIterceptor implements MethodInterceptor {

	private final Object realObj;

	public JCRTransactionalIterceptor(Object realObj) {
		this.realObj = realObj;
	}

	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		Object result;
		/*
		 * get @Transactional annotation on field
		 */
		final Transactional transactional = method.getAnnotation(Transactional.class);
		if (null != transactional) {
			/*
			 * get the JCR context
			 */
			JcrPersistenceContext jcrPersistenceContext = getJcrPersistenceContent();
			try {
				/*
				 * invoke
				 */
				result = method.invoke(realObj, objects);
			} catch (final Exception e) {
				throw new RuntimeException("Exception in invoke ", e);
			} finally {
			}
		} else {
			result = method.invoke(realObj, objects);
		}
		return result;
	}

	private JcrPersistenceContext getJcrPersistenceContent() throws Exception {
		try {
			return null;
		} catch (Exception e) {
			throw new Exception("Exception in getJcrPersistenceContent", e);
		}
	}
}
