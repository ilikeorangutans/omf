package org.om.jcr2pojo.entitymappingbuilder.namingstrategy;

import javax.jcr.Node;

import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * 
 * @author tome
 * 
 */
public interface ClassNamingStrategy {

	String generateName(Node node) throws JCRException;
}
