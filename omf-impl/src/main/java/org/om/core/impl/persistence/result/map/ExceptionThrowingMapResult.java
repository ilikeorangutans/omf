package org.om.core.impl.persistence.result.map;

import java.util.*;

import org.om.core.api.persistence.result.*;

public class ExceptionThrowingMapResult implements MapResult {
	@Override
	public Map<?, ?> getValue() {
		throw new IllegalStateException("No value available.");
	}

	@Override
	public boolean hasResult() {
		return false;
	}
}
