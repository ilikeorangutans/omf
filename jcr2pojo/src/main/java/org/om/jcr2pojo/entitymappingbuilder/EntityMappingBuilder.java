package org.om.jcr2pojo.entitymappingbuilder;

import javax.jcr.Node;
import javax.jcr.Session;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.persistence.jcr.exception.JCRException;

public interface EntityMappingBuilder {

	/**
	 * build entity mapping
	 */
	public abstract EntityMapping build(Node node) throws JCRException;

	/**
	 * build entity mapping
	 */
	public abstract EntityMapping build(String jcrPath, Session session) throws JCRException;

}