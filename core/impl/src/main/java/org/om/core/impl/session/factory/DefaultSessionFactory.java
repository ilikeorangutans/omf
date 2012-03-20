package org.om.core.impl.session.factory;

import org.om.core.api.persistence.PersistenceDelegateFactory;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.mapping.registry.OnDemandMappingRegistry;
import org.om.core.impl.persistence.cglib.CglibProxyFactory;
import org.om.core.impl.persistence.interceptor.factory.PersistenceInterceptorFactoryImpl;

/**
 * 
 * @author tome
 * 
 */
public class DefaultSessionFactory extends ImmutableSessionFactory {

	public DefaultSessionFactory(PersistenceDelegateFactory persistenceDelegateFactory) {
		super(persistenceDelegateFactory, new OnDemandMappingRegistry(new EntityMappingExtractorImpl()), new CglibProxyFactory(
				new PersistenceInterceptorFactoryImpl()));
	}
}
