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

import org.om.core.api.exception.*;

/**
 * Marks the given field as a mapped field.
 *
 * @author Jakob Külzer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mapped {
	/**
	 * The actual implementation type. This is necessary when the types of mapped fields are
	 * interfaces and you need to specify a subtype to actually use. This value is optional, and if
	 * not provided, it will fall back to the declared type of the field. This is usually sufficient
	 * if the the type of the actual mapped element is the same as the type that's provided via the
	 * generics.
	 * </p>
	 * However, in cases where the declared type of a field is not the actual entity, but a supertype
	 * thereof, it has to be specified. For example, if your interface is <tt>MyEntity</tt> but your
	 * implementation is <tt>MyEntityImpl</tt>:
	 * 
	 * <pre>
	 * <code>@Property
	 * &#64;Mapped(implementationType=MyEntityImpl.class)
	 * private MyEntity myEntity; 
	 * </code>
	 * </pre>
	 * 
	 * Same for collections:
	 * 
	 * <pre>
	 * <code>@Collection(targetType=MyEntity.class)
	 * List&lt;MyEntity&gt; list;</code>
	 * </pre>
	 * 
	 * However, if the generic type of the collection is different than the actual entity type, the
	 * mapping type has to be set explicitly. For example:
	 * 
	 * <pre>
	 * &#064;Collection(targetType = MyEntity.class)
	 * List&lt;MyEntityImpl&gt; list;
	 * </pre>
	 * <p>
	 * Please note that the implementation type must be either the same type as {@link #targetType()}
	 * or a subtype of it.
	 * 
	 * @return
	 */
	Class<?> implementationType() default NULL.class;

	/**
	 * Exception to be thrown when the mapped property cannot be retrieved from the underlying
	 * persistence layer and {@link #missingStrategy()} is set to
	 * {@link MissingStrategy#ThrowException}. The exception must have either a no-arg constructor or
	 * take one String parameter.
	 * 
	 * @return
	 */
	Class<? extends RuntimeException> missingException() default MissingException.class;

	/**
	 * Defines how object mapper will react to missing properties. The default is to return
	 * <tt>null</tt> if the referenced property cannot be found.
	 * 
	 * @see MissingStrategy
	 * @return
	 */
	MissingStrategy missingStrategy() default MissingStrategy.ReturnNull;
}
