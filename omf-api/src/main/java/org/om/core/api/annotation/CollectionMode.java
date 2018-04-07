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
 * Describes how a collection is to be created from the underlying nodes after Object Mapper has resolved the collection base using {@link Collection#location()} setting. Note that some of the
 * settings are only applicable for {@link Map}s.
 *
 * @author Jakob Külzer
 */
public enum CollectionMode {
   /**
    * Construct a collection from the child nodes of the collection base node.
    */
   Children,
   /**
    * Treat the collection base node as a multi-valued property and use the property values to build the collection.
    */
   MultiValueProperty,
   /**
    * Creates a collection from all properties found on the base node.
    */
   Properties
}
