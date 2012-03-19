package org.om.jcr2pojo.reveng;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.jcr2pojo.classgenerator.DAOGenerator;
import org.om.jcr2pojo.classgenerator.POJOGenerator;
import org.om.jcr2pojo.entitymappingbuilder.EntityMappingBuilder;
import org.om.jcr2pojo.entitymappingbuilder.impl.EntityMappingBuilderImpl;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.ClassNamingStrategy;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.PropertyNamingStrategy;

/**
 * 
 * @author tome
 * 
 */
public class JCRJavaPersistenceGenerator {
	/**
	 * class naming strategy
	 */
	private final ClassNamingStrategy classNamingStrategy;
	/**
	 * property naming strategy
	 */
	private final PropertyNamingStrategy propertyNamingStrategy;
	/**
	 * namespace to place classes in
	 */
	private final String namespace;
	/**
	 * root node to look at
	 */
	private final Node node;
	/**
	 * output path
	 */
	private final String outputPath;

	/**
	 * ctor
	 */
	public JCRJavaPersistenceGenerator(Node node, String namespace, String outputPath, ClassNamingStrategy classNamingStrategy,
			PropertyNamingStrategy propertyNamingStrategy) {
		this.node = node;
		this.namespace = namespace;
		this.classNamingStrategy = classNamingStrategy;
		this.propertyNamingStrategy = propertyNamingStrategy;
		this.outputPath = outputPath;
		/*
		 * make the output dir
		 */
		new File(outputPath + "/").mkdirs();
	}

	/**
	 * map nodes and produce java to the console
	 */
	public void execute() throws JCRException {
		try {
			/*
			 * get the mappings
			 */
			final HashMap<String, EntityMapping> mappings = mapNode(node);
			if ((null != mappings) && (mappings.size() > 0)) {
				/*
				 * walk
				 */
				final Iterator<String> iter = mappings.keySet().iterator();
				while (iter.hasNext()) {
					final String key = iter.next();
					final EntityMapping entityMapping = mappings.get(key);
					if (entityMapping.getPropertyMappings().getSize() > 0) {
						generatePOJO(entityMapping);
						generateDAO(entityMapping);
					}
				}
			}
		} catch (final Exception e) {
			throw new JCRException("Exception in reverseEngineer", e);
		}
	}

	/**
	 * generate DAO
	 */
	private void generateDAO(EntityMapping entityMapping) throws JCRException {
		try {
			final FileOutputStream fos = new FileOutputStream(outputPath + "/" + entityMapping.getName() + "DAO" + ".java");
			final DAOGenerator daoGenerator = new DAOGenerator();
			daoGenerator.generateDAO(entityMapping.getName() + "DAO", namespace, fos);
		} catch (final Exception e) {
			throw new JCRException("Exception in generateJava", e);
		}
	}

	/**
	 * generate POJO
	 */
	private void generatePOJO(EntityMapping entityMapping) throws JCRException {
		try {
			final FileOutputStream fos = new FileOutputStream(outputPath + "/" + entityMapping.getName() + ".java");
			final POJOGenerator pojoGenerator = new POJOGenerator();
			pojoGenerator.generatePOJO(namespace, entityMapping, fos);
		} catch (final Exception e) {
			throw new JCRException("Exception in generateJava", e);
		}
	}

	/**
	 * map all nodes that can be found
	 */
	private HashMap<String, EntityMapping> mapNode(Node node) throws JCRException {
		try {
			final HashMap<String, EntityMapping> ret = new HashMap<String, EntityMapping>();
			final EntityMappingBuilder entityMapper = new EntityMappingBuilderImpl(classNamingStrategy, propertyNamingStrategy);
			final NodeIterator iter = node.getNodes();
			while (iter.hasNext()) {
				final Node n = iter.nextNode();
				/*
				 * map
				 */
				final EntityMapping mapping = entityMapper.build(n);
				ret.put(mapping.getName(), mapping);
				/*
				 * node has nodes?
				 */
				if (node.hasNodes()) {
					final HashMap<String, EntityMapping> subCollection = mapNode(n);
					ret.putAll(subCollection);
				}
			}
			return ret;
		} catch (final Exception e) {
			throw new JCRException("Exception in mapNode", e);
		}
	}
}
