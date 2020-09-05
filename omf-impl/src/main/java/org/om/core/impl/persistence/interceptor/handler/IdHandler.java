package org.om.core.impl.persistence.interceptor.handler;

import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.interceptor.handler.*;

public class IdHandler implements ItemHandler {
	@Override
	public Object retrieve(MappedField mappedField, PersistenceAdapter adapter) {
		return adapter.getId();
	}
}
