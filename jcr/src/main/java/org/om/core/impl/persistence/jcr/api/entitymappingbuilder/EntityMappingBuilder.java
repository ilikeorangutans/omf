package org.om.core.impl.persistence.jcr.api.entitymappingbuilder;

import javax.jcr.Session;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.persistence.jcr.exception.JCRException;

public interface EntityMappingBuilder {

	/**
	 * build entity mapping
	 */
	public abstract EntityMapping build(String jcrPath, Session session) throws JCRException;

}