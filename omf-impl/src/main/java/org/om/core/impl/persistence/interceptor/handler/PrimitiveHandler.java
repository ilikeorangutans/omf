package org.om.core.impl.persistence.interceptor.handler;

import java.beans.*;

import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.field.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.interceptor.handler.*;
import org.om.core.api.persistence.request.*;
import org.om.core.api.persistence.result.*;
import org.om.core.impl.util.*;

/**
 * Property handler for primitive or autoboxed types. Will automatically perform type conversion if
 * necessary. TODO: This doesn't have support for parsing dates.
 *
 * @author Jakob Külzer
 */
public class PrimitiveHandler implements ItemHandler {
	@Override
	public Object retrieve(MappedField mappedField, PersistenceAdapter adapter) {
		if (!(mappedField.getMapping() instanceof PropertyMapping)) {
			throw new MappingException("Expected PropertyMapping for " + mappedField + " but got " + mappedField.getClass());
		}
		final PropertyMapping mapping = (PropertyMapping) mappedField.getMapping();
		final PersistenceResult result;
		try {
			result = adapter.getProperty(new ImmutablePersistenceRequest(mapping.getPropertyName(), mappedField.getType(), Mode.Relative));
		} catch (final PersistenceLayerException e) {
			throw new ObjectMapperException("Could not retrieve primitive for " + mappedField, e);
		}
		if (result == null) {
			throw new IllegalStateException("PersistenceAdapter returned null. This should not happen.");
		}
		final Object input;
		if (result.hasResult()) {
			input = result.getValue();
		} else {
			input = MissingHandler.INSTANCE.retrieve(mappedField, adapter);
		}
		// If we get a null, we can just return null, no type checking
		// necessary.
		if (input == null) {
			return null;
		}
		// If the returned object already has the correct type, just return it.
		if (mappedField.getType() == input.getClass()) {
			return input;
		}
		// If the input is a primitive type, it's probably safe to return.
		// TODO: I just wrote probably. So it's probably a bad thing.
		if (ClassUtils.isPrimitiveOrAutoboxed(mappedField.getType()) && ClassUtils.isPrimitiveOrAutoboxed(input.getClass())) {
			return input;
		}
		// This is ugly, but necessary as boxed types are not the same class
		// as primitive types.
		// If the returned object is a String, we'll have to parse it.
		if (String.class.equals(input.getClass())) {
			// This is real ugly: PropertyEditors return boxed primitive
			// types instead of exactly what you ask for.
			final PropertyEditor editor = PropertyEditorManager.findEditor(mappedField.getType());
			editor.setAsText((String) input);
			return editor.getValue();
		}
		throw new IllegalStateException("We have an unhandled type " + mappedField.getType() + ".");
	}
}
