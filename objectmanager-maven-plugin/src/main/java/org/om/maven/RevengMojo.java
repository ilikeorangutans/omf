package org.om.maven;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.om.core.impl.persistence.jcr.sessionfactory.impl.ParameterizedJCRSessionFactory;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.impl.DefaultPropertyNamingStrategy;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.impl.NodeNameClassNamingStrategy;
import org.om.jcr2pojo.reveng.JCRJavaPersistenceGenerator;

/**
 * @author tome
 * @goal reveng
 */
public class RevengMojo extends AbstractMojo {
	/**
	 * username
	 */
	private String username;
	/**
	 * password
	 */
	private String password;
	/**
	 * url
	 */
	private String url;
	/**
	 * workspace
	 */
	private String workspace;
	/**
	 * namespace
	 */
	private String namespace;
	/**
	 * root node
	 */
	private String jcrRoot;
	/**
	 * target dir
	 */
	private String targetDir = "target/reveng/";

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}

	/**
	 * default ctor
	 * <p>
	 * used by maven
	 * </p>
	 */
	public RevengMojo() {
	}

	public void execute() throws MojoExecutionException {
		try {
			final Session session = new ParameterizedJCRSessionFactory(url, workspace, username, password).getSession();
			if (null != session) {
				Node rootNode = session.getRootNode();
				if (null != jcrRoot) {
					rootNode = rootNode.getNode(jcrRoot);
				}
				if (null != rootNode) {
					final JCRJavaPersistenceGenerator engine = new JCRJavaPersistenceGenerator(rootNode, namespace, targetDir,
							new NodeNameClassNamingStrategy(), new DefaultPropertyNamingStrategy());
					engine.execute();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MojoExecutionException("Unable execute mojo", e);
		}
	}

	public String getJcrRoot() {
		return jcrRoot;
	}

	public String getNamespace() {
		return namespace;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setJcrRoot(String jcrRoot) {
		this.jcrRoot = jcrRoot;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
}