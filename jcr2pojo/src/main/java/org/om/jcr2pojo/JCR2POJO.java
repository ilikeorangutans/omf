package org.om.jcr2pojo;

import java.io.ByteArrayOutputStream;

import javax.jcr.Session;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.persistence.jcr.sessionfactory.impl.PropertiesConfiguredJCRSessionFactory;
import org.om.jcr2pojo.classgenerator.POJOGenerator;
import org.om.jcr2pojo.entitymappingbuilder.EntityMappingBuilder;
import org.om.jcr2pojo.entitymappingbuilder.impl.EntityMappingBuilderImpl;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.impl.DefaultPropertyNamingStrategy;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.impl.NodeIdentifierClassNamingStrategy;

/**
 * 
 * @author tome
 * 
 */
public class JCR2POJO {
	/**
	 * stuff we need
	 */
	private static EntityMappingBuilder entityMappingBuilder = new EntityMappingBuilderImpl(new NodeIdentifierClassNamingStrategy(),
			new DefaultPropertyNamingStrategy());
	private static POJOGenerator pojoGenerator = new POJOGenerator();

	/**
	 * Ah "C", I knew you well!
	 */
	public static void main(String args[]) throws java.io.IOException {
		/*
		 * path
		 */
		final String jcrPath = args[0];
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
				pojoGenerator.generatePOJO(packageName, entityMapping, baos);
				System.out.println(baos.toString());
			}
			/*
			 * done
			 */
			session.logout();
		}
	}
}
