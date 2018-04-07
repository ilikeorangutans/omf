package org.om.core.impl.mapping.registry;

import javax.inject.Inject;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.extractor.EntityMappingExtractor;
import org.om.core.api.mapping.registry.MappingRegistry;

/**
 * Simple {@link MappingRegistry} that scans types as they come in.
 *
 * @author Jakob Külzer
 */
public class OnDemandMappingRegistry implements MappingRegistry {
   private final EntityMappingExtractor entityMappingExtractor;

   @Inject
   public OnDemandMappingRegistry(EntityMappingExtractor entityMappingExtractor) {
      this.entityMappingExtractor = entityMappingExtractor;
   }

   @Override
   public EntityMapping getMapping(Class<?> clazz) {
      return entityMappingExtractor.extract(clazz);
   }
}
