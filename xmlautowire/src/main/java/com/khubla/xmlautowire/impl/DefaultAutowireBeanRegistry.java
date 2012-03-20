package com.khubla.xmlautowire.impl;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.beanutils.ConstructorUtils;

import com.khubla.xmlautowire.AutowireBeanRegistry;
import com.khubla.xmlautowire.exception.AutowireBeanRegistryException;
import com.khubla.xmlautowire.xml.Argument;
import com.khubla.xmlautowire.xml.AutowireBeanRegistryXMLMarshaller;
import com.khubla.xmlautowire.xml.Bean;
import com.khubla.xmlautowire.xml.Beans;

/**
 * 
 * @author tome
 * 
 */
public class DefaultAutowireBeanRegistry implements AutowireBeanRegistry {

	/**
	 * default bean file
	 */
	private final static String DEFAULT_BEAN_FILE = "/autobeans.xml";

	/**
	 * beans
	 */
	private final Hashtable<String, Object> beanCache = new Hashtable<String, Object>();
	/**
	 * bean definitions
	 */
	private Hashtable<String, Bean> beanDefinitions = null;

	public Object getBean(String name) throws AutowireBeanRegistryException {
		try {
			if (null != name) {
				Object ret = beanCache.get(name);
				if (null != ret) {
					/*
					 * was in cache
					 */
					return ret;
				} else {
					/*
					 * get bean definition
					 */
					final Bean bean = beanDefinitions.get(name);
					if (null != bean) {
						/*
						 * create it
						 */
						ret = instantiateBean(bean);
						/*
						 * done
						 */
						return ret;
					} else {
						throw new Exception("Unknown bean name '" + name + "'");
					}
				}
			} else {
				return null;
			}
		} catch (final Exception e) {
			throw new AutowireBeanRegistryException("Exception in get", e);
		}
	}

	/**
	 * instantiate bean. This is 2-recursive. "instantiateBean" calls "getBean"
	 * on referenced beans, which in turn calls "instantiateBean". This allows
	 * us to create beans which are declared in the XML before the beans they
	 * reference.
	 */
	private Object instantiateBean(Bean bean) throws AutowireBeanRegistryException {
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
						final Object o = getBean(arg.getValue());
						if (null != o) {
							arguments[i] = o;
						} else {
							throw new AutowireBeanRegistryException("Unable to find argument named '" + arg.getValue() + "' for bean '" + bean.getName() + "'");
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
			final Object o = ConstructorUtils.invokeConstructor(clazz, arguments);
			/*
			 * cache?
			 */
			if (bean.isCache()) {
				beanCache.put(bean.getName(), o);
			}
			/*
			 * done
			 */
			return o;
		} catch (final Exception e) {
			throw new AutowireBeanRegistryException("Exception in instantiateBean for bean '" + bean.getName() + "' of type '" + bean.getClazz() + "'", e);
		}

	}

	public void load() throws AutowireBeanRegistryException {
		try {
			InputStream inputStream = DefaultAutowireBeanRegistry.class.getResourceAsStream(DEFAULT_BEAN_FILE);
			if (null != inputStream) {
				load(inputStream);
			} else {
				throw new Exception("Unable to find '" + DEFAULT_BEAN_FILE + "'");
			}
		} catch (Exception e) {
			throw new AutowireBeanRegistryException("Exception in load", e);
		}
	}

	public void load(InputStream inputStream) throws AutowireBeanRegistryException {
		try {
			/*
			 * read the xml
			 */
			final Beans beans = AutowireBeanRegistryXMLMarshaller.unmarshall(inputStream);
			if (null != beans) {
				beanDefinitions = new Hashtable<String, Bean>();
				final List<Bean> lst = beans.getBean();
				if ((null != lst) && (lst.size() > 0)) {
					for (int i = 0; i < lst.size(); i++) {
						final Bean bean = lst.get(i);
						beanDefinitions.put(bean.getName().trim(), bean);
					}
				}
			}
			/*
			 * autocreate
			 */
			preInstantiateBeans();
		} catch (final Exception e) {
			throw new AutowireBeanRegistryException("Exception in load", e);
		}
	}

	/**
	 * create and cache all the autocreate beans
	 */
	private void preInstantiateBeans() throws AutowireBeanRegistryException {
		try {
			final Enumeration<String> enumer = beanDefinitions.keys();
			while (enumer.hasMoreElements()) {
				final String key = enumer.nextElement();
				final Bean bean = beanDefinitions.get(key);
				if (bean.isAutocreate()) {
					instantiateBean(bean);
				}
			}
		} catch (final Exception e) {
			throw new AutowireBeanRegistryException("Exception in preInstantiateBeans", e);
		}
	}
}
