package org.om.core.impl.persistence.jcr.impl.entitymappingbuilder.namingstrategy;

import javax.jcr.Node;

import org.om.core.impl.persistence.jcr.api.entitymappingbuilder.namingstrategy.ClassNamingStrategy;
import org.om.core.impl.persistence.jcr.exception.JCRException;

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
