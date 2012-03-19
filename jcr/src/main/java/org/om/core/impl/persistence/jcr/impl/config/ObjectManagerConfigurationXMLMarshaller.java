package org.om.core.impl.persistence.jcr.impl.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.om.core.impl.persistence.jcr.impl.config.ObjectManagerConfig;

/**
 * @author tom
 */
@SuppressWarnings("restriction")
public class ObjectManagerConfigurationXMLMarshaller {
	/**
	 * marshall
	 */
	public static String marshall(ObjectManagerConfig objectManagerConfig) throws Exception {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			marshall(objectManagerConfig, baos);
			return baos.toString();
		} catch (final Exception e) {
			throw new Exception("Exception in marshall", e);
		}
	}

	/**
	 * marshall
	 */
	public static void marshall(ObjectManagerConfig objectManagerConfig, OutputStream outputStream) throws Exception {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(ObjectManagerConfig.class);
			final Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			final SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			final Schema schema = schemaFactory.newSchema(ObjectManagerConfigurationXMLMarshaller.class.getResource("/objectmanagerconfig.xsd"));
			marshaller.setSchema(schema);
			marshaller.marshal(objectManagerConfig, outputStream);
		} catch (final Exception e) {
			throw new Exception("Exception in marshall", e);
		}
	}

	/**
	 * unmarshall
	 */
	public static ObjectManagerConfig unmarshall(InputStream xml) throws Exception {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(ObjectManagerConfig.class);
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			final SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			final Schema schema = schemaFactory.newSchema(ObjectManagerConfigurationXMLMarshaller.class.getResource("/objectmanagerconfig.xsd"));
			unmarshaller.setSchema(schema);
			return (ObjectManagerConfig) unmarshaller.unmarshal(xml);
		} catch (final Exception e) {
			throw new Exception("Exception in unmarshall", e);
		}
	}

	/**
	 * unmarshall
	 */
	public static ObjectManagerConfig unmarshall(String xml) throws Exception {
		try {
			return unmarshall(new ByteArrayInputStream(xml.getBytes()));
		} catch (final Exception e) {
			throw new Exception("Exception in unmarshall", e);
		}
	}
}
