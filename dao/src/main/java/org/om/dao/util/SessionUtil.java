package org.om.dao.util;

import org.om.core.api.session.Session;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.persistence.jcr.JcrPersistenceContext;
import org.om.core.impl.persistence.jcr.JcrPersistenceDelegateFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.session.factory.ImmutableSessionFactory;
import org.om.dao.config.ObjectManagerConfiguration;
import org.om.dao.config.impl.XMLObjectManagerConfiguration;

/**
 * 
 * @author tome
 * 
 */
public class SessionUtil {

	/**
	 * the OM config
	 */
	private static ObjectManagerConfiguration objectManagerConfiguration = null;

	/**
	 * get OM session
	 */
	public static Session getSession() throws JCRException {
		try {
			/*
			 * init if needed
			 */
			init();
			/*
			 * the session
			 */
			final SessionFactory sessionFactory = new ImmutableSessionFactory(new JcrPersistenceDelegateFactory(), new OnDemandMappingRegistry(
					new EntityMappingExtractorImpl()), new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));

			return sessionFactory.getSession(new JcrPersistenceContext(objectManagerConfiguration.getJCRSessionFactory().getSession()));

		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}

	/**
	 * init
	 */
	private static void init() throws JCRException {
		try {
			if (null == objectManagerConfiguration) {
				objectManagerConfiguration = XMLObjectManagerConfiguration.getObjectManagerConfiguration();
			}
		} catch (final Exception e) {
			throw new JCRException("Exception constructing init", e);
		}
	}
}
