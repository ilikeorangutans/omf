package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.session.Session;
import org.om.core.impl.test.EntityWithPrimitiveProperties;
import org.om.core.impl.test.MappedFieldBuilder;

@RunWith(JMock.class)
public class ReferencePropertyHandlerTest {
   private Session session;
   private final Mockery mockery = new JUnit4Mockery();

   @Before
   public void setUp() {
      session = mockery.mock(Session.class);
   }

   @Test
   @Ignore
   public void test() {
      mockery.checking(new Expectations() {
         {
            oneOf(session).get(EntityWithPrimitiveProperties.class, "/foo/bar");
         }
      });
      final ReferenceHandler handler = new ReferenceHandler(session);
      final PersistenceAdapter delegate = new TestingPassThroughPersistenceAdapter("/foo/bar");
      final MappedField mappedField = new MappedFieldBuilder().withName("mappedField").withType(EntityWithPrimitiveProperties.class)
            .withReferenceMapping("fieldname", EntityWithPrimitiveProperties.class, "path").create();
      final Object retrieve = handler.retrieve(mappedField, delegate);
      assertThat(retrieve, notNullValue());
      System.out.println("ReferencePropertyHandlerTest.test() " + retrieve);
      fail("implement me");
   }
}
