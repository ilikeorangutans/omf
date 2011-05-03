package com.omf.om.api.mapping;

public class ImmutableAtomMappingImpl implements AtomMapping {

	private final String fieldName;
	private final String atomName;
	private final String container;

	public ImmutableAtomMappingImpl(String fieldName, String atomName,
			String container) {
		this.fieldName = fieldName;
		this.atomName = atomName;
		this.container = container;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImmutableAtomMappingImpl other = (ImmutableAtomMappingImpl) obj;
		if (atomName == null) {
			if (other.atomName != null)
				return false;
		} else if (!atomName.equals(other.atomName))
			return false;
		if (container == null) {
			if (other.container != null)
				return false;
		} else if (!container.equals(other.container))
			return false;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		return true;
	}

	public String getAtomName() {
		return atomName;
	}

	public String getContainerName() {
		return container;
	}

	public String getFieldName() {
		return fieldName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((atomName == null) ? 0 : atomName.hashCode());
		result = prime * result
				+ ((container == null) ? 0 : container.hashCode());
		result = prime * result
				+ ((fieldName == null) ? 0 : fieldName.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "ImmutableAtomMappingImpl [fieldName=" + fieldName
				+ ", atomName=" + atomName + ", container=" + container + "]";
	}

}
