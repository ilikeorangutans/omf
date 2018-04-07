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

public class ImmutablePersistenceRequest implements PersistenceRequest {
   private final Class<?> exptectedType;
   private final Mode mode;
   private final String path;

   public ImmutablePersistenceRequest(String path, Class<?> exptectedType, Mode mode) {
      this.path = path;
      this.exptectedType = exptectedType;
      this.mode = mode;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final ImmutablePersistenceRequest other = (ImmutablePersistenceRequest) obj;
      if (exptectedType == null) {
         if (other.exptectedType != null) {
            return false;
         }
      } else if (!exptectedType.equals(other.exptectedType)) {
         return false;
      }
      if (mode != other.mode) {
         return false;
      }
      if (path == null) {
         if (other.path != null) {
            return false;
         }
      } else if (!path.equals(other.path)) {
         return false;
      }
      return true;
   }

   @Override
   public Class<?> getExpectedType() {
      return exptectedType;
   }

   @Override
   public Mode getMode() {
      return mode;
   }

   @Override
   public String getPath() {
      return path;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((exptectedType == null) ? 0 : exptectedType.hashCode());
      result = (prime * result) + ((mode == null) ? 0 : mode.hashCode());
      result = (prime * result) + ((path == null) ? 0 : path.hashCode());
      return result;
   }

   @Override
   public String toString() {
      return "ImmutablePersistenceRequest [mode=" + mode + ", exptectedType=" + exptectedType + ", path=" + path + "]";
   }
}
