package com.omf.om.api.mapping;

import java.util.Collections;
import java.util.List;

public class ImmutableEntityMappingImpl implements EntityMapping {

	private final List<AtomMapping> atomMappings;
	private final Class<? extends Object> clazz;

	public ImmutableEntityMappingImpl(Class<? extends Object> clazz,
			List<AtomMapping> atomMappings) {
		this.clazz = clazz;
		this.atomMappings = Collections.unmodifiableList(atomMappings);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImmutableEntityMappingImpl other = (ImmutableEntityMappingImpl) obj;
		if (atomMappings == null) {
			if (other.atomMappings != null)
				return false;
		} else if (!atomMappings.equals(other.atomMappings))
			return false;
		if (clazz == null) {
			if (other.clazz != null)
				return false;
		} else if (!clazz.equals(other.clazz))
			return false;
		return true;
	}

	public List<AtomMapping> getAtomMappings() {
		return atomMappings;
	}

	public Class<?> getEntityClass() {
		return clazz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((atomMappings == null) ? 0 : atomMappings.hashCode());
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "ImmutableEntityMappingImpl [atomMappings=" + atomMappings
				+ ", clazz=" + clazz + "]";
	}
}
