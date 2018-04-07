package org.om.core.impl.persistence.jcr.util;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Retrieves nodes from a JCR session.
 *
 * @author Jakob Külzer
 */
public class NodeRetriever {
   private final Session session;

   public NodeRetriever(Session session) {
      this.session = session;
   }

   /**
    * Retrieves a {@link Node} with the given path. The path will always be resolved relative to the root node.
    * 
    * @param path
    * @return
    * @throws PathNotFoundException
    * @throws RepositoryException
    */
   public Node getNode(String path) throws PathNotFoundException, RepositoryException {
      return getNode(path, session.getRootNode());
   }

   /**
    * Retrieves a {@link Node} relative to the given context node. However, if the given path denotes an absolute path, the node will be retrieved relative to the root node.
    * 
    * @param path
    * @param context
    * @return
    * @throws PathNotFoundException
    * @throws RepositoryException
    */
   public Node getNode(String path, Node context) throws PathNotFoundException, RepositoryException {
      final boolean absolutePath = path.startsWith("/");
      if (absolutePath) {
         context = session.getRootNode();
         path = path.substring(1);
      }
      return context.getNode(path);
   }
}
