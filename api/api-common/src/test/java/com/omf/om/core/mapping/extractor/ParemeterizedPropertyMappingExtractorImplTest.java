package com.omf.om.core.mapping.extractor;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.omf.om.api.annotation.PropertyMissingStrategy;
import com.omf.om.api.exception.PropertyMissingException;
import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.core.mapping.EntityWithPlainProperties;

@RunWith(Parameterized.class)
public class ParemeterizedPropertyMappingExtractorImplTest {

	private final Class<?> type;
	private final String fieldName;
	private final String propertyName;
	private final String defaultValue;
	private final PropertyMissingStrategy missingStrategy;
	private final Class<? extends Exception> missingException;

	@Parameters
	public static List<Object[]> getParameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { EntityWithPlainProperties.class, "fieldWithDefaultSettings",
				"fieldWithDefaultSettings", "", PropertyMissingStrategy.ReturnNull, PropertyMissingException.class });
		list.add(new Object[] { EntityWithPlainProperties.class, "fieldWithDefaultValue",
				"fieldWithDefaultValue", "1234", PropertyMissingStrategy.ReturnNull, PropertyMissingException.class });
		list.add(new Object[] { EntityWithPlainProperties.class, "fieldWithCustomName", "customName", "",
				PropertyMissingStrategy.ReturnNull, PropertyMissingException.class });
		list.add(new Object[] { EntityWithPlainProperties.class, "fieldWithMissingStrategy",
				"fieldWithMissingStrategy", "", PropertyMissingStrategy.DefaultValue, PropertyMissingException.class });
		list.add(new Object[] { EntityWithPlainProperties.class, "fieldWithAllSettings", "customName",
				"custom default value", PropertyMissingStrategy.ThrowException, RuntimeException.class });

		return list;
	}

	public ParemeterizedPropertyMappingExtractorImplTest(Class<?> type, String fieldName, String propertyName,
			String defaultValue, PropertyMissingStrategy missingStrategy, Class<? extends Exception> missingException) {
		this.type = type;
		this.fieldName = fieldName;
		this.propertyName = propertyName;
		this.defaultValue = defaultValue;
		this.missingStrategy = missingStrategy;
		this.missingException = missingException;
	}

	@Test
	public void test() throws Exception {
		Field field = type.getDeclaredField(fieldName);

		PropertyMapping mapping = new PropertyMappingExtractorImpl().fromField(field);

		assertThat(mapping, notNullValue());
		assertThat(mapping.getFieldname(), is(fieldName));
		assertThat(mapping.getPropertyName(), is(propertyName));
		assertThat(mapping.getDefaultValue(), is(defaultValue));
		assertThat(mapping.getMissingStrategy(), is(missingStrategy));
		assertEquals(missingException, mapping.getMissingException());
	}
}
