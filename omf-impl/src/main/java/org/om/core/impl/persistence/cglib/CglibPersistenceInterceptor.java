package org.om.core.impl.persistence.cglib;

import static org.om.core.impl.util.ClassUtils.*;

import java.lang.reflect.*;

import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.persistence.interceptor.*;
import org.slf4j.*;

import net.sf.cglib.proxy.*;

/**
 * {@link MethodInterceptor} that will intercept all calls to getters for mapped entities. All other
 * method calls will be dispatched to the original object.
 *
 * @author Jakob Külzer
 */
public class CglibPersistenceInterceptor implements MethodInterceptor {
	private static final Logger LOG = LoggerFactory.getLogger(CglibPersistenceInterceptor.class);
	private final EntityMapping entityMapping;
	private final PersistenceInterceptor interceptor;

	public CglibPersistenceInterceptor(EntityMapping entityMapping, PersistenceInterceptor interceptor) {
		this.interceptor = interceptor;
		this.entityMapping = entityMapping;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		final String name = method.getName();
		LOG.debug("Intercepted method {}()", name);
		final boolean isGetter = isGetter(name);
		if (!isGetter) {
			LOG.trace("Method {}() not a getter, not intercepted.", name);
			return proxy.invokeSuper(obj, args);
		}
		final String fieldName = extractFieldName(name);
		if (!entityMapping.hasField(fieldName)) {
			LOG.trace("Method {}() mapped to field {}. No mapping for property, method not intercepted.", name, fieldName);
			return proxy.invokeSuper(obj, args);
		}
		final MappedField mappedField = entityMapping.getByFieldName(fieldName);
		LOG.trace("Retrieved {}", mappedField);
		try {
			return interceptor.get(mappedField);
		} catch (final MappingException e) {
			e.setMappedType(entityMapping.getTypeClass());
			throw e;
		}
	}
}
