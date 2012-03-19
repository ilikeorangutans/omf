package org.om.dynabean.impl;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.beanutils.ConstructorUtils;
import org.om.dynabean.DynabeanRegistry;
import org.om.dynabean.exception.DynabeanException;
import org.om.dynabean.xml.DynabeansXMLMarshaller;

import com.om.dynabean.xml.Argument;
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
			Object[] arguments = null;
			if (null != bean.getArguments()) {
				final List<Argument> allArguments = bean.getArguments().getArgument();
				arguments = new Object[allArguments.size()];
				for (int i = 0; i < allArguments.size(); i++) {
					/*
					 * single argument
					 */
					final Argument arg = allArguments.get(i);
					if (arg.isReference()) {
						/*
						 * resolve the argument by name
						 */
						final Object o = find(arg.getValue());
						if (null != o) {
							arguments[i] = o;
						} else {
							throw new DynabeanException("Unable to find argument named '" + arg.getValue() + "' for bean '" + bean.getName() + "'");
						}
					} else {
						arguments[i] = arg.getValue();
					}
				}
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
			throw new DynabeanException("Exception in instantiateBean for bean '" + bean.getName() + "' of type '" + bean.getClazz() + "'", e);
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
