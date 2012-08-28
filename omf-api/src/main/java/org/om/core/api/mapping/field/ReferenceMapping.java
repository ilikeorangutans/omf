package org.om.core.api.mapping.field;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.LookupMode;

/**
 * A ReferenceMapping is a mapping that describes a one-to-one relationship
 * between two {@link Entity}s.
 * 
 * @author Jakob Külzer
 * 
 */
public interface ReferenceMapping extends Mapping {

	LookupMode getLookupMode();

	/**
	 * Returns the path relative to the initial node that hosts the referenced
	 * entity.
	 * 
	 * @return
	 */
	String getPath();

}
