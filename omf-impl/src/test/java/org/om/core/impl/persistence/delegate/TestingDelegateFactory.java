package org.om.core.impl.persistence.delegate;

import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;

/**
 * @author Jakob Külzer
 */
public class TestingDelegateFactory implements PersistenceAdapterFactory {
	@Override
	public PersistenceAdapter create(Object id, EntityMapping mapping, PersistenceContext persistenceContext) {
		return new TestingPersistenceAdapter(mapping, persistenceContext);
	}
}
