package org.om.core.api.persistence.request;

public class ImmutablePersistenceRequest implements PersistenceRequest {

	private final Class<?> exptectedType;
	private final Mode mode;
	private final String path;

	public ImmutablePersistenceRequest(String path, Class<?> exptectedType, Mode mode) {
		this.path = path;
		this.exptectedType = exptectedType;
		this.mode = mode;
	}

	@Override
	public Class<?> getExpectedType() {
		return exptectedType;
	}

	@Override
	public Mode getMode() {
		return mode;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "ImmutablePersistenceRequest [mode=" + mode + ", exptectedType=" + exptectedType + ", path=" + path
				+ "]";
	}

}
