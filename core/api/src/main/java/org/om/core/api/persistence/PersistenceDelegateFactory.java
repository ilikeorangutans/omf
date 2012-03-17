package org.om.core.api.persistence;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.session.Session;

public interface PersistenceDelegateFactory {

	PersistenceDelegate create(Session session, Object id, EntityMapping mapping, PersistenceContext persistenceContext);

}
