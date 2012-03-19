package org.om.dynabean.impl;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.beanutils.ConstructorUtils;
import org.om.dynabean.DynabeanRegistry;
import org.om.dynabean.exception.DynabeanException;
import org.om.dynabean.xml.DynabeansXMLMarshaller;

import com.om.dynabean.xml.Bean;
import com.om.dynabean.xml.Beans;

/**
 * 
 * @author tome
 * 
 */
public class DefaultDynabeanRegistry implements DynabeanRegistry {

	/**
	 * beans
	 */
	private Hashtable<String, Object> dynabeans = null;

	public Object find(String name) throws DynabeanException {
		try {
			return dynabeans.get(name);
		} catch (final Exception e) {
			throw new DynabeanException("Exception in find", e);
		}
	}

	/**
	 * instantiate bean
	 */
	private Object instantiateBean(Bean bean) throws DynabeanException {
		try {
			/*
			 * collect the arguments
			 */
			final Object[] arguments = new Object[bean.getArguments().getArgument().size()];
			for (int i = 0; i < bean.getArguments().getArgument().size(); i++) {
				arguments[i] = bean.getArguments().getArgument().get(i);
			}
			/*
			 * get the class
			 */
			final Class<?> clazz = Class.forName(bean.getClazz().trim());
			/*
			 * create
			 */
			return ConstructorUtils.invokeConstructor(clazz, arguments);

		} catch (final Exception e) {
			throw new DynabeanException("Exception in instantiateBean ctor", e);
		}

	}

	public void load(InputStream inputStream) throws DynabeanException {
		try {
			/*
			 * read the xml
			 */
			final Beans beans = DynabeansXMLMarshaller.unmarshall(inputStream);
			if (null != beans) {
				dynabeans = new Hashtable<String, Object>();
				final List<Bean> lst = beans.getBean();
				if ((null != lst) && (lst.size() > 0)) {
					for (int i = 0; i < lst.size(); i++) {
						final Bean bean = lst.get(i);
						final Object o = instantiateBean(bean);
						dynabeans.put(bean.getName().trim(), o);
					}
				}
			}
		} catch (final Exception e) {
			throw new DynabeanException("Exception in load", e);
		}

	}
}
