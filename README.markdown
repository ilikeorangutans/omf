Object Mapper
=============

A JPA like framework for JCR
---------------------------- 


Short Example
=============

Entity
------

Very simple entity:

	@Entity
	public class MyPojo {
		
		/**
		 * ID property, used to identify an entity. 
		 */
		@Id	@Property
		private String id;
		
		/**
		 * A regular property of primitive type.
		 */
		@Property
		private String name
		
		/**
		 * Default constructor, required for creating dynamic proxies.
		 */
		public MyPojo() {
		}
		
		public String getId() { return id; }
		
		public String getName() { return name; }
	}
	
And that's it. That's all you need.

Retrieving Entities
-------------------

Retrieving instances is accomplished via a Session object. You can retrieve a session from a SessionFactory with a persistence context. In this example
we'll use a JcrPersistenceContext:   	

	PersistenceContext context = new JcrPersistenceContext(jcrSession);
	SessionFactory sessionFactory = ...; // Obtain session factory
	
	Session session = sessionFactory.getSession(context); // Get a session
	String id = "/path/to/node";
	
	MyPojo myPojo = session.get(MyPojo.class, id); // Retrieve the actual object
	
	// And now use the object...
	
	myPojo.getName();
	
	
	
	