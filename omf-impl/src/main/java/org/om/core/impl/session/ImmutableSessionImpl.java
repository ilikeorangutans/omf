package org.om.core.impl.session;

import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.registry.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.proxy.*;
import org.om.core.api.session.*;
import org.slf4j.*;

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

	public ImmutableSessionImpl(PersistenceContext persistenceContext, PersistenceAdapterFactory persistenceDelegateFactory, MappingRegistry mappingRegistry, ProxyFactory proxyFactory) {
		this.persistenceContext = persistenceContext;
		this.persistenceDelegateFactory = persistenceDelegateFactory;
		this.mappingRegistry = mappingRegistry;
		this.proxyFactory = proxyFactory;
		LOGGER.debug("New session with context {}", persistenceContext);
	}

	@Override
	public void close() throws ObjectMapperException {
		throw new ObjectMapperException("not implemented");
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Object o) throws ObjectMapperException {
		throw new ObjectMapperException("not implemented");
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Object id) throws ObjectMapperException {
		if (clazz == null) {
			throw new NullPointerException("Class is null.");
		}
		final EntityMapping entityMapping = mappingRegistry.getMapping(clazz);
		final PersistenceAdapter persistenceAdapter = persistenceDelegateFactory.create(id, entityMapping, persistenceContext);
		LOGGER.debug("Retrieving entity of type {} from {}", clazz, id);
		return (T) proxyFactory.create(this, entityMapping, persistenceAdapter);
	}

	@Override
	public void save(Object o) throws ObjectMapperException {
		try {
			mappingRegistry.getMapping(o.getClass());
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
		} catch (final Exception e) {
			throw new ObjectMapperException("Exception in save", e);
		}
	}
}
