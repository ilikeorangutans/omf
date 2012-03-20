package org.om.dynabean.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.om.dynabean.xml.Beans;

/**
 * @author tom
 */
@SuppressWarnings("restriction")
public class DynabeansXMLMarshaller {
	/**
	 * marshall
	 */
	public static String marshall(Beans beans) throws Exception {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			marshall(beans, baos);
			return baos.toString();
		} catch (final Exception e) {
			throw new Exception("Exception in marshall", e);
		}
	}

	/**
	 * marshall
	 */
	public static void marshall(Beans beans, OutputStream outputStream) throws Exception {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(Beans.class);
			final Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			final SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			final Schema schema = schemaFactory.newSchema(DynabeansXMLMarshaller.class.getResource("/dynabeans.xsd"));
			marshaller.setSchema(schema);
			marshaller.marshal(beans, outputStream);
		} catch (final Exception e) {
			throw new Exception("Exception in marshall", e);
		}
	}

	/**
	 * unmarshall
	 */
	public static Beans unmarshall(InputStream xml) throws Exception {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(Beans.class);
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			final SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			final Schema schema = schemaFactory.newSchema(DynabeansXMLMarshaller.class.getResource("/dynabeans.xsd"));
			unmarshaller.setSchema(schema);
			return (Beans) unmarshaller.unmarshal(xml);
		} catch (final Exception e) {
			throw new Exception("Exception in unmarshall", e);
		}
	}

	/**
	 * unmarshall
	 */
	public static Beans unmarshall(String xml) throws Exception {
		try {
			return unmarshall(new ByteArrayInputStream(xml.getBytes()));
		} catch (final Exception e) {
			throw new Exception("Exception in unmarshall", e);
		}
	}
}
