package org.om.jcr2pojo.reveng;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.persistence.jcr.api.entitymappingbuilder.EntityMappingBuilder;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.impl.entitymappingbuilder.EntityMappingBuilderImpl;
import org.om.jcr2pojo.classgenerator.POJOGenerator;

/**
 * 
 * @author tome
 * 
 */
public class ReverseEngineeringEngine {

	/**
	 * namespace to place classes in
	 */
	private final String namespace;
	/**
	 * root node to look at
	 */
	private final Node node;

	/**
	 * ctor
	 */
	public ReverseEngineeringEngine(Node node, String namespace) {
		this.node = node;
		this.namespace = namespace;

	}

	/**
	 * generate .java file
	 */
	private void generateJava(String nodeId, EntityMapping entityMapping) throws JCRException {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final POJOGenerator pojoGenerator = new POJOGenerator();
			pojoGenerator.generatePOJO(nodeId, namespace, entityMapping, baos);
			System.out.println(baos.toString());
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
			final NodeIterator iter = node.getNodes();
			while (iter.hasNext()) {
				final Node n = iter.nextNode();
				/*
				 * map
				 */
				final EntityMappingBuilder entityMapper = new EntityMappingBuilderImpl();
				final EntityMapping mapping = entityMapper.build(n);
				ret.put(node.getIdentifier(), mapping);
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

	/**
	 * map nodes and produce java to the console
	 */
	public void execute() throws JCRException {
		try {
			/*
			 * get the mappings
			 */
			final HashMap<String, EntityMapping> mappings = mapNode(this.node);
			if ((null != mappings) && (mappings.size() > 0)) {
				/*
				 * walk
				 */
				final Iterator<String> iter = mappings.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					final EntityMapping entityMapping = mappings.get(key);
					if (entityMapping.getPropertyMappings().getSize() > 0) {
						generateJava(key, entityMapping);
					}
				}
			}
		} catch (final Exception e) {
			throw new JCRException("Exception in reverseEngineer", e);
		}
	}
}
