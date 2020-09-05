package org.om.core.impl.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.om.core.impl.util.ClassUtils.*;

import org.junit.*;

public class ClassUtilsTest {
	@Test
	public void testExtractFieldName() {
		assertThat(extractFieldName("getFoobar"), is("foobar"));
		assertThat(extractFieldName("isFoobar"), is("foobar"));
		assertThat(extractFieldName("getXYZ"), is("xYZ"));
	}

	@Test
	public void testIsGetter() {
		assertThat(isGetter("getFoobar"), is(true));
		assertThat(isGetter("getfoobar"), is(false));
		assertThat(isGetter("doSomething"), is(false));
		assertThat(isGetter("isAwesome"), is(true));
	}
}
