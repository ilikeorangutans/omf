package org.om.core.api.mapping;

import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Mapped;

/**
 * Describes the persistence manager specific part of a @{@link Mapped} field.
 * 
 * @author Jakob Külzer
 * 
 */
public interface Mapping {

	/**
	 * Returns true if this mapping is an {@link Id} mapping.
	 * 
	 * @return
	 */
	boolean isId();

	/**
	 * Returns if the type of the property is either Java simple type (int,
	 * float, double, ...) or java.lang.String.
	 * 
	 * @return
	 */
	boolean isPrimitiveOrWrappedType();

}
