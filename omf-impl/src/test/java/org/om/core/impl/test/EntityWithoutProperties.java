package org.om.core.impl.test;

import org.om.core.api.annotation.Entity;

/**
 * Not a valid entity as it doesn't have an id field.
 *
 * @author Jakob Külzer
 */
@Entity
public class EntityWithoutProperties {
   public EntityWithoutProperties() {
   }
}
