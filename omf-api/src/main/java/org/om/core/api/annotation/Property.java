/*
 * Copyright 2012 Jakob Külzer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.om.core.api.annotation;

import java.lang.annotation.*;

/**
 * Annotation that describes a property on an {@link Entity}.
 *
 * @author Jakob Külzer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
	static final String DEFAULT_VALUE = "";

	/**
	 * Default value to use if {@link #missingStrategy()} is set to
	 * {@link MissingStrategy#DefaultValue}. The returned value must be translatable into the type of
	 * the field, otherwise a conversion error will occur.
	 * 
	 * @return
	 */
	String defaultValue() default DEFAULT_VALUE;

	/**
	 * Configures how to retrieve the value for this property. This only affects reference
	 * properties, i.e. fields that reference a non-primitive type.
	 * 
	 * @return
	 */
	LookupMode lookupMode() default LookupMode.Reference;

	/**
	 * Name of the property. If not set, will use the POJO field name.
	 * 
	 * @return
	 */
	String name() default "";
}
