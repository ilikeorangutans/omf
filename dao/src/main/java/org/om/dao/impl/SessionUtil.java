package org.om.dao.impl;

import org.om.core.api.session.Session;
import org.om.core.api.session.factory.SessionFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;
import org.om.core.impl.persistence.jcr.JcrPersistenceContext;
import org.om.core.impl.persistence.jcr.JcrPersistenceDelegateFactory;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.PropertiesConfiguredJCRSessionFactory;
import org.om.core.impl.session.factory.ImmutableSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class SessionUtil {
	/**
	 * get OM session
	 */
	public static Session getSession() throws JCRException {
		try {
			/*
			 * test some OM stuff
			 */
			final SessionFactory sessionFactory = new ImmutableSessionFactory(new JcrPersistenceDelegateFactory(), new OnDemandMappingRegistry(
					new EntityMappingExtractorImpl()), new CglibProxyFactory(new PersistenceInterceptorFactoryImpl()));

			return sessionFactory.getSession(new JcrPersistenceContext(new PropertiesConfiguredJCRSessionFactory().getSession()));

		} catch (final Exception e) {
			throw new JCRException("Exception in getSession", e);
		}
	}
}
