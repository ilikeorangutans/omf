package org.om.core.api.persistence.request;

public interface PersistenceRequest {

	Class<?> getExpectedType();

	Mode getMode();

	String getPath();

}
