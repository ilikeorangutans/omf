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
 * Exception thrown when a mapped property could not be resolved. TODO: Isn't this the same as {@link PathNotFoundException}?
 *
 * @author Jakob Külzer
 */
public class MissingException extends ObjectMapperException {
   private static final long serialVersionUID = 1L;

   public MissingException(String message) {
      super(message);
   }

   public MissingException(String message, Throwable throwable) {
      super(message, throwable);
   }

   public MissingException(Throwable throwable) {
      super(throwable);
   }
}
