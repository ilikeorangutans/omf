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

import java.util.Map;

/**
 * Defines how to create the keys for {@link Map}s.
 *
 * @author Jakob Külzer
 */
public enum MapKeyStrategy {
   /**
    * Use the name of the node as the key.
    */
   Name,
   /**
    * Use a property under each entry's node. This only works for complex target types.
    */
   Property,
   /**
    * Use a zero based index as key. This is hardly useful, but I just felt there might be situations where this is handy.
    */
   Index
}
