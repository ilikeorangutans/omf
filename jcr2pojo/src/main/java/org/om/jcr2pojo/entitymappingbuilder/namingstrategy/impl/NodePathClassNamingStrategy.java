package org.om.jcr2pojo.entitymappingbuilder.namingstrategy.impl;

import javax.jcr.Node;

import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.ClassNamingStrategy;

/**
 * 
 * @author tome
 * 
 */
public class NodePathClassNamingStrategy implements ClassNamingStrategy {

	public String generateName(Node node) throws JCRException {
		try {
			return node.getPath();
		} catch (final Exception e) {
			throw new JCRException("Exception in generateName", e);
		}
	}
}
