package org.om.core.impl.persistence.interceptor.handler;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.interceptor.handler.PropertyHandler;

public class PrimitivePropertyHandler implements PropertyHandler {

	public Object retrieve(PropertyMapping propertyMapping, Object input) {
		if (input == null)
			return null;

		// If the returned object already has the correct type, just return it.
		if (propertyMapping.getPropertyType() == input.getClass()) {
			return input;
		}

		// If the returned object is a String, we'll have to parse it.
		if (String.class.equals(input.getClass())) {

			// This is real ugly: PropertyEditors return boxed primitive
			// types instead of exactly what you ask for.
			final PropertyEditor editor = PropertyEditorManager.findEditor(propertyMapping.getPropertyType());
			editor.setAsText((String) input);
			return editor.getValue();
		}

		assert false : "We have an unhandled primitive type " + propertyMapping.getPropertyType() + ".";
		return null;
	}

}
