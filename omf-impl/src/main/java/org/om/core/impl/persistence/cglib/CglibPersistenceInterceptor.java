package org.om.core.impl.persistence.cglib;

import static org.om.core.impl.util.ClassUtils.extractFieldName;
import static org.om.core.impl.util.ClassUtils.isGetter;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.interceptor.PersistenceInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link MethodInterceptor} that will intercept all calls to getters for mapped
 * entities. All other method calls will be dispatched to the original object.
 * 
 * @author Jakob Külzer
 * 
 */
public class CglibPersistenceInterceptor implements MethodInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(CglibPersistenceInterceptor.class);

	private final EntityMapping entityMapping;
	private final PersistenceInterceptor interceptor;

	public CglibPersistenceInterceptor(EntityMapping entityMapping, PersistenceInterceptor interceptor) {
		this.interceptor = interceptor;
		this.entityMapping = entityMapping;
	}

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
		} catch (MappingException e) {
			e.setMappedType(entityMapping.getTypeClass());
			throw e;
		}
	}

}
