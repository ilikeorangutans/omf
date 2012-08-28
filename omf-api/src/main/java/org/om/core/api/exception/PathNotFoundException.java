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

import org.om.core.api.path.Path;

/**
 * Thrown by a persistence backend if the given path cannot be resolved.
 * 
 * @author Jakob Külzer
 * 
 */
public class PathNotFoundException extends ObjectMapperException {

	private static final long serialVersionUID = -8842387827650563782L;

	private final Path path;

	public PathNotFoundException(Path path) {
		super(path.toString() + " not found.");
		this.path = path;
	}

	public PathNotFoundException(Path path, String message) {
		super(message);
		this.path = path;
	}

	public PathNotFoundException(Path path, String message, Throwable cause) {
		super(message, cause);
		this.path = path;
	}

	public PathNotFoundException(Path path, Throwable cause) {
		super(cause);
		this.path = path;
	}

	public Path getPath() {
		return path;
	}
}
