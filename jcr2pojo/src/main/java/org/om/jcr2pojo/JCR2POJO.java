package org.om.jcr2pojo;

import java.io.ByteArrayOutputStream;

import javax.jcr.Session;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.PropertiesConfiguredJCRSessionFactory;
import org.om.jcr2pojo.classexporter.ClassExporter;
import org.om.jcr2pojo.mappingbuilder.EntityMappingBuilder;

/**
 * 
 * @author tome
 * 
 */
public class JCR2POJO {
	/**
	 * stuff we need
	 */
	private static EntityMappingBuilder entityMappingBuilder = new EntityMappingBuilder();
	private static ClassExporter classExporter = new ClassExporter();

	/**
	 * Ah "C", I knew you well!
	 */
	public static void main(String args[]) throws java.io.IOException {
		/*
		 * path
		 */
		final String jcrPath = args[0];
		/*
		 * classname
		 */
		final String className = args[1];
		/*
		 * package name
		 */
		final String packageName = args[2];
		/*
		 * get a session
		 */
		final Session session = new PropertiesConfiguredJCRSessionFactory().getSession();
		if (null != session) {
			/*
			 * build the mappings
			 */
			final EntityMapping entityMapping = entityMappingBuilder.build(jcrPath, session);
			if (null != entityMapping) {
				/*
				 * build the class
				 */
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				classExporter.exportClass(className, packageName, entityMapping, baos);
				System.out.println(baos.toString());
			}
			/*
			 * done
			 */
			session.logout();
		}
	}
}
