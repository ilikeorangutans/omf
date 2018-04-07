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
package org.om.core.api.path;

/**
 * Describes a path. A path has always the following form:
 * <ul>
 * <li>Starts with a slash
 * <li>has zero or more segments, delimited by a slash each
 * </ul>
 * This class is immutable, i.e. instances, once created, cannot be modified. All methods that modify the path, return a new instance.
 *
 * @author Jakob Külzer
 */
public class Path {
   public static final String SLASH = "/";
   private final int length;
   private final String path;

   public Path(String path) {
      if (path == null) {
         throw new RuntimeException("Cannot construct path from null");
      }
      this.path = cleanPath(path);
      length = calculateLength();
   }

   public Path append(String append) {
      return new Path(path + SLASH + append);
   }

   private int calculateLength() {
      if (isRoot()) {
         return 0;
      }
      int counter = 0;
      for (final char c : path.toCharArray()) {
         if (c == SLASH.charAt(0)) {
            counter++;
         }
      }
      return counter;
   }

   private String cleanPath(String path) {
      // Remove whitespace:
      path = path.trim();
      // Remove multiple path delimiters:
      path = path.replaceAll("//+", "/");
      // Remove trailing slash:
      path = !path.equals(SLASH) && (path.length() > 2) && path.endsWith(SLASH) ? path.substring(0, path.length() - 1) : path;
      return path;
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
      final Path other = (Path) obj;
      if (path == null) {
         if (other.path != null) {
            return false;
         }
      } else if (!path.equals(other.path)) {
         return false;
      }
      return true;
   }

   /**
    * Returns the lenght of this path in segments. If this path only describes root, this method will return 0.
    * 
    * @return
    */
   public int getLength() {
      return length;
   }

   public Path getParent() {
      if (isRoot()) {
         return this;
      }
      return new Path(path.substring(0, path.lastIndexOf(SLASH) + 1));
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((path == null) ? 0 : path.hashCode());
      return result;
   }

   /**
    * Retuns true if this path describes only the root.
    * 
    * @return
    */
   public boolean isRoot() {
      return path.equals(SLASH);
   }

   @Override
   public String toString() {
      return path;
   }
}
