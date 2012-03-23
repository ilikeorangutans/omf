package org.om.core.api.persistence;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.session.Session;

/**
 * @author Jakob Külzer
 * @author tome
 */
public interface PersistenceDelegateFactory {

	PersistenceDelegate create(Session session, Object id, EntityMapping mapping, PersistenceContext persistenceContext, boolean createNode);

}
