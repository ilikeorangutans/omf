package org.om.core.api.persistence.request;

public interface PersistenceRequest {

	Mode getMode();

	String getPath();

	Class<?> getExpectedType();

}
