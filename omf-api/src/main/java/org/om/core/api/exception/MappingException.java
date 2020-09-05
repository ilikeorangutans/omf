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
package org.om.core.api.exception;

/**
 * Exception thrown if a mapping related error has been detected.
 *
 * @author Jakob Külzer
 */
public class MappingException extends ObjectMapperException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private Class<?> mappedType;

	public MappingException(Class<?> type, String string) {
		this(String.format("Type %s is not an entity: %s", type.getName(), string));
	}

	public MappingException(String message) {
		super(message);
	}

	public MappingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MappingException(Throwable cause) {
		super(cause);
	}

	public Class<?> getMappedType() {
		return mappedType;
	}

	@Override
	public String getMessage() {
		String message = super.getMessage();
		if (getMappedType() != null) {
			message += " (in " + getMappedType().getName() + ")";
		}
		return message;
	}

	public void setMappedType(Class<?> mappedType) {
		this.mappedType = mappedType;
	}
}
