package org.om.core.impl.mapping.extractor;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.om.core.api.exception.MappingException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.Fields;
import org.om.core.impl.test.EntityWithInvalidCollectionMapping;
import org.om.core.impl.test.EntityWithPrimitiveProperties;
import org.om.core.impl.test.EntityWithoutProperties;

@SuppressWarnings("unused")
public class EntityMappingExtractorImplTest {
   private final EntityMappingExtractorImpl extractor = new EntityMappingExtractorImpl();

   @Before
   public void setUp() throws Exception {
   }

   @Test
   public void testEntityWithBasicProperties() {
      final EntityMapping mapping = extractor.extract(EntityWithPrimitiveProperties.class);
      assertThat(mapping, notNullValue());
      assertEquals(EntityWithPrimitiveProperties.class, mapping.getTypeClass());
      assertThat(mapping.getMappedFields().getSize(), is(EntityWithPrimitiveProperties.NUMBER_OF_FIELDS));
   }

   @Test(expected = MappingException.class)
   public void testEntityWithoutAnnotation() throws Exception {
      extractor.extract(getClass());
   }

   @Test(expected = MappingException.class)
   public void testEntityWithoutIdProperty() {
      extractor.extract(EntityWithoutProperties.class);
   }

   @Test(expected = MappingException.class)
   public void testExtractWithInvalidCollectionMapping() {
      extractor.extract(EntityWithInvalidCollectionMapping.class);
   }

   @Test
   public void testSimpleTypeWithBasicProperties() {
      final Fields extract = extractor.extractFields(EntityWithPrimitiveProperties.class);
      assertThat(extract.isEmpty(), is(false));
      assertThat(extract.getSize(), is(EntityWithPrimitiveProperties.NUMBER_OF_FIELDS));
   }

   @Test
   public void testSimpleTypeWithoutProperties() {
      final Fields extract = extractor.extractFields(EntityWithoutProperties.class);
      assertThat("Non-entites should not generate any mapped fields.", extract.isEmpty(), is(true));
   }
}
