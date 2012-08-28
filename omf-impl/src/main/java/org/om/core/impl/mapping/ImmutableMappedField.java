package org.om.core.impl.mapping;

import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.Mapping;

public class ImmutableMappedField implements MappedField {

	private final Mapping mapping;

	private final Class<? extends RuntimeException> missingException;

	private final MissingStrategy missingStrategy;
	private final String name;
	private final Class<?> type;

	public ImmutableMappedField(String name, Class<?> type, Mapping mapping, MissingStrategy missingStrategy, Class<? extends RuntimeException> missingException) {
		this.name = name;
		this.type = type;
		this.mapping = mapping;
		this.missingStrategy = missingStrategy;
		this.missingException = missingException;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImmutableMappedField other = (ImmutableMappedField) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public Mapping getMapping() {
		return mapping;
	}

	public Class<RuntimeException> getMissingException() {
		return (Class<RuntimeException>) missingException;
	}

	public MissingStrategy getMissingStrategy() {
		return missingStrategy;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "ImmutableMappedField [mapping=" + mapping + ", missingException=" + missingException + ", missingStrategy=" + missingStrategy + ", name="
				+ name + ", type=" + type + "]";
	}

}
