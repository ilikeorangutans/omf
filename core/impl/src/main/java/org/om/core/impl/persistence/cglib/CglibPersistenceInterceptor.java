package org.om.core.impl.persistence.cglib;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.Mapping;
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
	private static final Pattern PATTERN = Pattern.compile("(get|is)([A-Z])([a-zA-Z0-9_]*)");

	private final EntityMapping entityMapping;
	private final PersistenceInterceptor interceptor;

	public CglibPersistenceInterceptor(EntityMapping entityMapping, PersistenceInterceptor interceptor) {
		this.interceptor = interceptor;
		this.entityMapping = entityMapping;
	}

	public String extractFieldName(String name) {
		Matcher matcher = PATTERN.matcher(name);
		matcher.find();
		final String fieldName = matcher.group(2).toLowerCase() + matcher.group(3);
		return fieldName;
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

		final Mapping mapping = entityMapping.getMappingByField(fieldName);
		LOG.trace("Retrieved property mapping {}", mapping);

		return interceptor.getProperty(mapping);
	}

	/**
	 * Returns true if the given String is a Java Beans getter.
	 * 
	 * @param name
	 * @return
	 */
	public boolean isGetter(String name) {
		return PATTERN.matcher(name).find();
	}

}
