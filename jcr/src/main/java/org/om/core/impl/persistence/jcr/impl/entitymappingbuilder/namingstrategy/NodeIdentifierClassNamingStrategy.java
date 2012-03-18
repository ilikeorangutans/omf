package org.om.core.impl.persistence.jcr.impl.entitymappingbuilder.namingstrategy;

import javax.jcr.Node;

import org.om.core.impl.persistence.jcr.api.entitymappingbuilder.namingstrategy.ClassNamingStrategy;
import org.om.core.impl.persistence.jcr.exception.JCRException;

/**
 * 
 * @author tome
 *         <p>
 *         Name the generated java classes based on the Node ID
 *         </p>
 * 
 */
public class NodeIdentifierClassNamingStrategy implements ClassNamingStrategy {

	public String generateName(Node node) throws JCRException {
		try {
			return new String("Id_") + node.getIdentifier().replaceAll("-", "_");
		} catch (final Exception e) {
			throw new JCRException("Exception in generateName", e);
		}
	}

}
