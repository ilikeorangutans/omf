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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Maps a collection.
 * 
 * Please note that depending on the concrete collection implementation in use,
 * you'll want to make sure that your collection entries implement
 * {@link #hashCode()} and {@link #equals(Object)}.
 * 
 * @author Jakob Külzer
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Collection {

	public static final String LOCATION_RELATIVE_USING_FIELDNAME = "";

	/**
	 * Describes the location of where to load the collection from. This has
	 * implications as of how the actual backend will load the data.
	 * 
	 * There are two possible ways of specifying a path to a collection. It can
	 * either be an absolute location, starting with a slash, or relative,
	 * without a beginning slash. If the location is relative, it will be
	 * resolved relative to the node of the containing {@link Entity}.
	 * 
	 * The default is to use the field name as a relative path.
	 */
	String location() default LOCATION_RELATIVE_USING_FIELDNAME;

	/**
	 * Declares the type of the collection entries. The class needs to be a
	 * valid {@link Entity}.
	 * 
	 * @return
	 */
	Class<?> targetType();

}
