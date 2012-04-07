package org.om.dao.annotation.transactional.filter;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;

import org.om.dao.annotation.transactional.Transactional;

import com.khubla.simpleioc.IOCBeanRegistry;
import com.khubla.simpleioc.exception.IOCException;
import com.khubla.simpleioc.filter.IOCInstantiationFilter;
import com.khubla.simpleioc.xml.Bean;

public class TransactionalIOCInstantiationFilter implements IOCInstantiationFilter {

	public Object filter(IOCBeanRegistry iocBeanRegistry, Object object, final Object originalObject, Bean bean) throws IOCException {
		try {
			if (shouldProxy(object)) {
				Enhancer e = new Enhancer();
				e.setSuperclass(object.getClass());
				e.setCallback(new JCRTransactionalIterceptor(originalObject));
				return e.create();
			} else {
				return object;
			}
		} catch (final Exception e) {
			throw new IOCException("Exception in filter", e);
		}
	}

	/**
	 * check if the object should be proxied
	 */
	private boolean shouldProxy(Object object) throws IOCException {
		try {
			/*
			 * class is transactional?
			 */
			Transactional transactional = object.getClass().getAnnotation(Transactional.class);
			if (null != transactional) {
				return true;
			}
			/*
			 * walk methods
			 */
			final Method[] methods = object.getClass().getDeclaredMethods();
			if (null != methods) {
				for (int i = 0; i < methods.length; i++) {
					transactional = methods[i].getAnnotation(Transactional.class);
					if (null != transactional) {
						return true;
					}
				}
			}
			return false;
		} catch (final Exception e) {
			throw new IOCException("Exception in shouldProxy", e);
		}
	}
}
