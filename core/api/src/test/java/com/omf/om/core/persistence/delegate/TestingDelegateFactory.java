package com.omf.om.core.persistence.delegate;

import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.persistence.PersistenceContext;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.persistence.PersistenceDelegateFactory;
import com.omf.om.api.session.Session;

public class TestingDelegateFactory implements PersistenceDelegateFactory {

	public PersistenceDelegate create(Session session, EntityMapping mapping, PersistenceContext persistenceContext) {
		return new TestingPersistenceDelegate(session, mapping);
	}

}
