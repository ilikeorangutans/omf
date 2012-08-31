package org.om.core.impl.persistence.result.map;

import java.util.Map;

import org.om.core.api.persistence.result.MapResult;

public class ExceptionThrowingMapResult implements MapResult {

	@Override
	public boolean hasResult() {
		return false;
	}

	@Override
	public Map<?, ?> getValue() {
		throw new IllegalStateException("No value available.");
	}
}
