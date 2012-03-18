package org.om.jcr2pojo.reveng;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
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
	private void generateJava(EntityMapping entityMapping) throws JCRException {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final POJOGenerator pojoGenerator = new POJOGenerator();
			pojoGenerator.generatePOJO("TestClass", namespace, entityMapping, baos);
			System.out.println(baos.toString());
		} catch (final Exception e) {
			throw new JCRException("Exception in generateJava", e);
		}
	}

	/**
	 * map all nodes that can be found
	 */
	private Collection<EntityMapping> mapNode(Node node) throws JCRException {
		try {
			final Collection<EntityMapping> ret = new ArrayList<EntityMapping>();
			final NodeIterator iter = node.getNodes();
			while (iter.hasNext()) {
				final Node n = iter.nextNode();
				/*
				 * map
				 */
				final EntityMappingBuilder entityMapper = new EntityMappingBuilderImpl();
				final EntityMapping mapping = entityMapper.build(n);
				ret.add(mapping);
				/*
				 * node has nodes?
				 */
				if (node.hasNodes()) {
					final Collection<EntityMapping> subCollection = mapNode(n);
					ret.addAll(subCollection);
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
			final Collection<EntityMapping> mappings = mapNode(this.node);
			if ((null != mappings) && (mappings.size() > 0)) {
				/*
				 * walk
				 */
				final Iterator<EntityMapping> iter = mappings.iterator();
				while (iter.hasNext()) {
					final EntityMapping entityMapping = iter.next();
					if (entityMapping.getPropertyMappings().getSize() > 0) {
						generateJava(entityMapping);
					}
				}
			}
		} catch (final Exception e) {
			throw new JCRException("Exception in reverseEngineer", e);
		}
	}
}
