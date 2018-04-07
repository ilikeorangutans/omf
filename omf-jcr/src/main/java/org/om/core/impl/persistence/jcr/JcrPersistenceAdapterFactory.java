package org.om.core.impl.persistence.jcr;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.path.Path;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.PersistenceAdapterFactory;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.impl.persistence.jcr.util.NodeRetriever;

/**
 * @author Jakob Külzer
 * @author tome
 */
public class JcrPersistenceAdapterFactory implements PersistenceAdapterFactory {
   @Override
   public PersistenceAdapter create(Object id, EntityMapping mapping, PersistenceContext persistenceContext) {
      final JcrPersistenceContext context = (JcrPersistenceContext) persistenceContext;
      final String path;
      // This should return an exception better reflecting this error
      // condition. This can happen when an OMF session resolves a reference
      // but the reference field is null. Either we add better error handling
      // into the persistence adapters to intercept relative paths that are
      // null or we throw an exception that better indicates what happened.
      if (id == null) {
         throw new NullPointerException("Got null ID while creating adapter for " + mapping);
      }
      if (id instanceof String) {
         path = (String) id;
      } else if (id instanceof Path) {
         path = ((Path) id).toString();
      } else {
         throw new IllegalArgumentException("Not sure how to deal with ID of type " + id.getClass().getName() + ": " + id);
      }
      final NodeRetriever nodeRetriever = new NodeRetriever(context.getSession());
      try {
         final Node node = nodeRetriever.getNode(path);
         // if (true == rootNode.hasNode(path)) {
         // } else if (createNode) {
         // TODO: If the node cannot be found, we should return a proxy
         // object that records all changes and can be flushed if the
         // transaction is committed.
         // node = rootNode.addNode(path);
         // }
         return new JcrPersistenceAdapter(mapping, node);
      } catch (final PathNotFoundException e) {
         throw new org.om.core.api.exception.PathNotFoundException(new Path(path));
      } catch (final RepositoryException e) {
         throw new ObjectMapperException("Could not create persistence delegate", e);
      }
   }
}
