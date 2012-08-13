package org.om.core.impl.session;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.registry.MappingRegistry;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.PersistenceAdapterFactory;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.api.persistence.proxy.ProxyFactory;
import org.om.core.api.session.Session;
import org.om.core.impl.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jakob Külzer
 * @author tom
 */
public class ImmutableSessionImpl implements Session {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImmutableSessionImpl.class);

	private final MappingRegistry mappingRegistry;

	private final PersistenceContext persistenceContext;
	private final PersistenceAdapterFactory persistenceDelegateFactory;
	private final ProxyFactory proxyFactory;

	public ImmutableSessionImpl(PersistenceContext persistenceContext, PersistenceAdapterFactory persistenceDelegateFactory, MappingRegistry mappingRegistry,
			ProxyFactory proxyFactory) {
		this.persistenceContext = persistenceContext;
		this.persistenceDelegateFactory = persistenceDelegateFactory;
		this.mappingRegistry = mappingRegistry;
		this.proxyFactory = proxyFactory;

		LOGGER.info("New session with context {}", persistenceContext);
	}

	public void close() throws ObjectMapperException {
		throw new ObjectMapperException("not implemented");
	}

	public void commit() {
		// TODO Auto-generated method stub
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
		final PersistenceAdapter persistenceAdapter = persistenceDelegateFactory.create(this, id, entityMapping, persistenceContext, false);

		LOGGER.debug("Retrieving entity of type {} from {}", clazz, id);

		return (T) proxyFactory.create(this, entityMapping, persistenceAdapter);
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
			//Object id = EntityUtils.getEntityId(entityMapping, o);
			/*
			 * get a persistence delegate
			 */
			//final PersistenceAdapter persistenceAdapter = persistenceDelegateFactory.create(this, id, entityMapping, persistenceContext, true);
			/*
			 * walk the fields and save them
			 */

			// Iterator<Mapping> iter =
			// entityMapping.getItemMappings().getAll().iterator();
			// while (iter.hasNext()) {
			// /*
			// * get property
			// */
			// PropertyMapping propertyMapping = (PropertyMapping) iter.next();
			// /*
			// * save it
			// */
			// persistenceAdapter.setProperty(propertyMapping,
			// EntityUtils.getEntityPropertyValue(propertyMapping, o));
			// }
			throw new UnsupportedOperationException("Implement me.");
		} catch (Exception e) {
			throw new ObjectMapperException("Exception in save", e);
		}
	}
}
