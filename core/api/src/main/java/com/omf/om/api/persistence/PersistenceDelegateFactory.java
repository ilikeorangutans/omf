package com.omf.om.api.persistence;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.session.Session;

public interface PersistenceDelegateFactory {

	PersistenceDelegate create(Session session, Object id, EntityMapping mapping, PersistenceContext persistenceContext);

}
