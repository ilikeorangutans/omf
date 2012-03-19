package org.om.core.impl.persistence.jcr.impl.config;

import java.io.InputStream;

import org.apache.commons.beanutils.ConstructorUtils;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.persistence.jcr.api.config.ObjectManagerConfiguration;
import org.om.core.impl.persistence.jcr.api.sessionfactory.JCRSessionFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;

import com.om.core.impl.persistence.jcr.impl.config.Jcrfactory;
import com.om.core.impl.persistence.jcr.impl.config.ObjectManagerConfig;

/**
 * 
 * @author tome
 * 
 */
public class XMLObjectManagerConfiguration implements ObjectManagerConfiguration {

	/**
	 * OM session factory
	 */
	private SessionFactory sessionFactory;
	/**
	 * JCR session factory
	 */
	private JCRSessionFactory jcrSessionFactory;

	/**
	 * singleton
	 */
	private static XMLObjectManagerConfiguration instance = null;

	/**
	 * private ctor
	 */
	private XMLObjectManagerConfiguration() throws JCRException {
		try {
			parseConfiguration();
		} catch (Exception e) {
			throw new JCRException("Exception in XMLObjectManagerConfiguration ctor", e);
		}
	}

	/**
	 * singleton getter
	 */
	public static ObjectManagerConfiguration getObjectManagerConfiguration() throws JCRException {
		if (null == instance) {
			instance = new XMLObjectManagerConfiguration();
		}
		return instance;
	}

	public SessionFactory getSession() throws Exception {
		return this.sessionFactory;
	}

	public JCRSessionFactory getJCRSessionFactory() throws Exception {
		return this.jcrSessionFactory;
	}

	/**
	 * parse the XML configuration
	 */
	private void parseConfiguration() throws JCRException {
		try {
			/*
			 * get file
			 */
			InputStream inputStream = XMLObjectManagerConfiguration.class.getResourceAsStream(ObjectManagerConfiguration.DEFAULT_OBJECTMANAGER_CONFIGURATION);
			if (null != inputStream) {
				/*
				 * parse XML
				 */
				ObjectManagerConfig objectManagerConfig = ObjectManagerConfigurationXMLMarshaller.unmarshall(inputStream);
				/*
				 * the JCR factory
				 */
				this.jcrSessionFactory = createJCRSessionFactory(objectManagerConfig.getJcrfactory());
			} else {
				throw new Exception("Unable to load '" + ObjectManagerConfiguration.DEFAULT_OBJECTMANAGER_CONFIGURATION + "'");
			}
		} catch (Exception e) {
			throw new JCRException("Exception in parseConfiguration ctor", e);
		}
	}

	private JCRSessionFactory createJCRSessionFactory(Jcrfactory jcrfactory) throws JCRException {
		try {
			/*
			 * collect the arguments
			 */
			Object[] arguments = new Object[jcrfactory.getArguments().getArgument().size()];
			for (int i = 0; i < jcrfactory.getArguments().getArgument().size(); i++) {
				arguments[i] = jcrfactory.getArguments().getArgument().get(i);
			}
			/*
			 * get the class
			 */
			Class clazz = Class.forName(jcrfactory.getClazz());
			/*
			 * create
			 */
			JCRSessionFactory ret = (JCRSessionFactory) ConstructorUtils.invokeConstructor(clazz, arguments);
			/*
			 * done
			 */
			return ret;
		} catch (Exception e) {
			throw new JCRException("Exception in createJCRSessionFactory ctor", e);
		}
	}
}
