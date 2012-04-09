package org.om.core.impl.persistence.interceptor.handler;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceDelegate;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;

/**
 * Property handler for primitive or autoboxed types. Will automatically perform
 * type conversion if necessary.
 * 
 * TODO: This doesn't have support for parsing dates.
 * 
 * @author Jakob Külzer
 * 
 */
public class PrimitivePropertyHandler implements ItemHandler {

	public Object retrieve(Mapping mapping, PersistenceDelegate delegate) {
		final Object input = delegate.getProperty((PropertyMapping) mapping);

		if (input == null)
			return null;

		// If the returned object already has the correct type, just return it.
		if (mapping.getFieldType() == input.getClass()) {
			return input;
		}

		// If the returned object is a String, we'll have to parse it.
		if (String.class.equals(input.getClass())) {

			// This is real ugly: PropertyEditors return boxed primitive
			// types instead of exactly what you ask for.
			final PropertyEditor editor = PropertyEditorManager.findEditor(mapping.getFieldType());
			editor.setAsText((String) input);
			return editor.getValue();
		}

		assert false : "We have an unhandled primitive type " + mapping.getFieldType() + ".";
		return null;
	}

}
