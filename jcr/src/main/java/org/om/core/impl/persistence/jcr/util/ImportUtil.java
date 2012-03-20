package org.om.core.impl.persistence.jcr.util;

import java.io.InputStream;

import javax.jcr.ImportUUIDBehavior;
import javax.jcr.Node;
import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.sessionfactory.impl.PropertiesConfiguredJCRSessionFactory;

/**
 * 
 * @author tome
 *         <p>
 *         A little helper for importing some data for testing purposes
 *         </p>
 * 
 */
public class ImportUtil {

	public static void importXML(String nodeName, InputStream xml) throws JCRException {
		try {
			final Session session = new PropertiesConfiguredJCRSessionFactory().getSession();
			RecursiveDelete.recursiveDelete(session.getRootNode());
			final Node node = session.getRootNode().addNode(nodeName, "nt:unstructured");
			session.importXML(node.getPath(), xml, ImportUUIDBehavior.IMPORT_UUID_COLLISION_REPLACE_EXISTING);
			session.save();
			session.logout();
		} catch (final Exception e) {
			throw new JCRException("Exception in importXML", e);
		}
	}
}
