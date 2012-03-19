package org.om.jcr2pojo.entitymappingbuilder.namingstrategy;

import javax.jcr.Property;

import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * 
 * @author tome
 * 
 */
public interface PropertyNamingStrategy {
	String generateName(Property property) throws JCRException;
}
