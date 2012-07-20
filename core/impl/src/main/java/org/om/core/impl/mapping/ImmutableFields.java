package org.om.core.impl.mapping;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.Fields;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.Mapping;

/**
 * Immutable implementation of {@link Fields}.
 * 
 * @author Jakob Külzer
 * 
 */
public class ImmutableFields implements Fields {

	private final Map<String, MappedField> fields;

	private MappedField id;

	public ImmutableFields(Set<MappedField> inputFields) {

		final Map<String, MappedField> tmpFields = new HashMap<String, MappedField>();

		for (MappedField mf : inputFields) {
			final Mapping mapping = mf.getMapping();

			if (mapping.isId()) {
				if (id != null)
					throw new MappingException("Found more than one @Id property!");

				id = mf;
			}

			tmpFields.put(mf.getName(), mf);
		}

		this.fields = Collections.unmodifiableMap(tmpFields);
	}

	public Collection<MappedField> getAll() {
		return fields.values();
	}

	public MappedField getField(String fieldname) {
		return fields.get(fieldname);
	}

	@Override
	public MappedField getFieldByMapping(Mapping mapping) {

		for (MappedField mf : fields.values()) {
			// This should use .equals(), but for now it works:
			if (mf.getMapping() == mapping)
				return mf;
		}

		throw new IllegalArgumentException("Looking for a mapped field with mapping " + mapping + " but it doesn't exist.");
	}

	public MappedField getIdProperty() {
		return id;
	}

	public int getSize() {
		return fields.size();
	}

	public boolean hasField(String name) {
		return fields.containsKey(name);
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}

	public Iterator<MappedField> iterator() {
		return fields.values().iterator();
	}

}
