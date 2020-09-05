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
package org.om.core.api.persistence.request;

import org.om.core.api.persistence.*;

/**
 * Describes a request for a {@link PersistenceAdapter}. The idea behind this class is that a
 * {@link PersistenceAdapter} needs some information in order to retrieve the appropriate
 * properties/entities. This interface describes the minimum a persistence adapter can expect.
 *
 * @author Jakob Külzer
 */
public interface PersistenceRequest {
	/**
	 * Returns the expected return type.
	 * 
	 * @return
	 */
	Class<?> getExpectedType();

	/**
	 * States how the given {@link #getPath()} should be resolved.
	 * 
	 * @return
	 */
	Mode getMode();

	/**
	 * Path used to resolve the request.
	 * 
	 * @return
	 */
	String getPath();
}
