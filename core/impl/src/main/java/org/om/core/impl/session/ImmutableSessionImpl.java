package org.om.core.impl.session;

import java.util.Iterator;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.mapping.registry.MappingRegistry;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.PersistenceDelegateFactory;
import org.om.core.api.persistence.proxy.ProxyFactory;
import org.om.core.api.session.Session;
import org.om.core.impl.util.EntityUtils;

/**
 * @author Jakob Külzer
 * @author tom
 */
public class ImmutableSessionImpl implements Session {

	private final MappingRegistry mappingRegistry;

	private final ProxyFactory proxyFactory;

	private final PersistenceContext persistenceContext;

	private final PersistenceDelegateFactory persistenceDelegateFactory;

	public ImmutableSessionImpl(PersistenceContext persistenceContext, PersistenceDelegateFactory persistenceDelegateFactory, MappingRegistry mappingRegistry,
			ProxyFactory proxyFactory) {
		this.persistenceContext = persistenceContext;
		this.persistenceDelegateFactory = persistenceDelegateFactory;
		this.mappingRegistry = mappingRegistry;
		this.proxyFactory = proxyFactory;
	}

	public void close() throws ObjectMapperException {
		throw new ObjectMapperException("not implemented");
	}

	public void delete(Object o) throws ObjectMapperException {
		throw new ObjectMapperException("not implemented");
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Object id) throws ObjectMapperException {
		if (clazz == null) {
			throw new NullPointerException("Class is null.");
		}

		final EntityMapping entityMapping = mappingRegistry.getMapping(clazz);
		final PersistenceDelegate persistenceDelegate = persistenceDelegateFactory.create(this, id, entityMapping, persistenceContext);
		return (T) proxyFactory.create(this, entityMapping, persistenceDelegate);
	}

	public void save(Object o) throws ObjectMapperException {
		try {
			/*
			 * get the entity mapping
			 */
			final EntityMapping entityMapping = mappingRegistry.getMapping(o.getClass());
			/*
			 * get the id
			 */
			Object id = EntityUtils.getEntityId(entityMapping, o);
			/*
			 * get a persistence delegate
			 */
			final PersistenceDelegate persistenceDelegate = persistenceDelegateFactory.create(this, id, entityMapping, persistenceContext);
			/*
			 * walk the fields and save them
			 */
			Iterator<PropertyMapping> iter = entityMapping.getPropertyMappings().getAll().iterator();
			while (iter.hasNext()) {
				/*
				 * get property
				 */
				PropertyMapping propertyMapping = iter.next();
				/*
				 * save it
				 */
				persistenceDelegate.setProperty(propertyMapping, EntityUtils.getEntityPropertyValue(propertyMapping, o));
			}
		} catch (Exception e) {
			throw new ObjectMapperException("Exception in save", e);
		}
	}

}
