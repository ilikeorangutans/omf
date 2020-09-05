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
package org.om.core.api.session.factory;

import org.om.core.api.persistence.*;
import org.om.core.api.session.*;

/**
 * A session factory creates instances of {@link Session}.
 *
 * @author Jakob Külzer
 */
public interface SessionFactory {
	/**
	 * @param persistenceContext
	 * @return
	 */
	Session getSession(PersistenceContext persistenceContext);
}
