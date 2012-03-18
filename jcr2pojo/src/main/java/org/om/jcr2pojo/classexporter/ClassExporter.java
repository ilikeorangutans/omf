package org.om.jcr2pojo.classexporter;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.PropertyMap;
import org.om.core.api.mapping.PropertyMapping;
import org.om.jcr2pojo.exception.JCR2POJOException;

/**
 * 
 * @author tome
 * 
 */
public class ClassExporter {

	/**
	 * generate a POJO
	 */
	public void exportClass(String className, String namespace, EntityMapping entityMapping, OutputStream outputStream) throws JCR2POJOException {
		try {
			/*
			 * writer
			 */
			final PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
			/*
			 * namespace
			 */
			writer.println("package " + namespace + ";");
			writer.println();
			/*
			 * mr. kulzer's magic imports
			 */
			writer.println("import org.om.core.api.annotation.Entity;");
			writer.println("import org.om.core.api.annotation.Id;");
			writer.println("import org.om.core.api.annotation.Property;");
			writer.println();
			/*
			 * some commentary at the top
			 */
			writer.println("/*");
			writer.println("* This is a generated class");
			writer.println("*/");
			writer.println();
			/*
			 * declare the class
			 */
			writer.println("@Entity");
			writer.println("publc class " + className + "{");
			writer.println();
			/*
			 * walk fields
			 */
			final PropertyMap propertyMap = entityMapping.getPropertyMappings();
			if (null != propertyMap) {
				final Collection<PropertyMapping> mappings = propertyMap.getAll();
				final Iterator<PropertyMapping> iter = mappings.iterator();
				while (iter.hasNext()) {
					final PropertyMapping propertyMapping = iter.next();
					/*
					 * typename
					 */
					final String typename = "String";
					final String fieldname = propertyMapping.getFieldname();

					/*
					 * declare property. for now, as string.
					 */
					writer.println("\t@Property");
					writer.println("\tprivate " + typename + " " + fieldname + ";");
					writer.println();
					/*
					 * getter
					 */
					writer.println("\tpublic " + typename + " get" + getMungedFieldName(fieldname) + "()");
					writer.println("\t{return " + fieldname + ";}");
					writer.println();
					/*
					 * setter
					 */
					writer.println("\tpublic " + typename + " set" + getMungedFieldName(fieldname) + "(" + typename + " " + fieldname + ")");
					writer.println("\t{this." + fieldname + "=" + fieldname + ";}");
					writer.println();
				}
			}
			/*
			 * end of class
			 */
			writer.println("}");
			/*
			 * done
			 */
			writer.flush();
			writer.close();
		} catch (final Exception e) {
			throw new JCR2POJOException("Exception in exportClass", e);
		}
	}

	/**
	 * *sigh*
	 */
	private String getMungedFieldName(String fieldName) {
		final char[] b = fieldName.toCharArray();
		b[0] = Character.toUpperCase(b[0]);
		return new String(b);
	}
}
