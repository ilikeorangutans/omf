Object Mapper
=============

A JPA like framework for JCR
----------------------------

Having worked with JCR based systems for a while, I could never shake off the feeling that I was writing 
code that would talk to the JCR and translate it into Java objects again and again. Every time I wrote a
solution like this, I ended up thinking, there must be a better way to do this. At the same time, I was
always fascinated by the Hibernate framework and how it offers so much amazing functionality and that 
gave me the initial idea to write something functionally similar but for JCR repositories. The result is
the Object Mapper Framework. 


What Does It Do?
----------------

The core idea is that most Java applications that interface with a JCR will eventually translate the data
represented by the node structure into some kind of Java object. This translation process is often done
by manually implementing that code for each involved type, which results in poorly implemented and 
inflexible data access code. Enter Object Mapper Framework. Object Mapper Framework gives you Java 
annotations to describe how you want Java objects to be mapped to JCR structures. With this mapping, 
Object Mapper can create proxies that will dynamically route access to mapped fields to JCR structures.

Doesn't That Exist Already?
---------------------------

Yes, there are a few projects that go in that same direction, however when I started creating this project
I was unaware and would have done so anyways, just to learn how a framework like this is being built. 

If you are interested, here's a quick and probably incomplete list of similar frameworks:

* http://incubator.apache.org/graffito/jcr-mapping/index.html (Seems to be dead)
* http://jackrabbit.apache.org/object-content-mapping.html (Not particularly active)
* http://code.google.com/p/jcrom/ (No recent activity)

Status
------

The current implementation works. It's by no means perfect nor complete, but it works reliably in a larger 
project. See the limitations section below to get a better understanding. 

Roadmap
-------

**Version 0.4.0** support for field-level intercepts

**Version 0.3.0** better OSGI integration

**Version 0.2.0** add support for Maps

**Version 0.1.0** dynamic reference lookup

Concepts
========

There's a few important building blocks in Object Mapper Framework:

Entity
------

An entity is a Java class that Object Mapper Framework maps to a JCR. An entity by itself does not contain 
information as it's merely a way to logically organize mapped fields. 

At its core an Entity is a Java class adhering to the Java Beans convention:

* No argument public constructor
* Getters and setters
*  annotated with the ``org.om.core.api.annotation.Entity`` annotation

	@Entity
	public class MyPojo {
	}

Mapped Field 
------------

A mapped field is the most granular building block in Object Mapper. It describes a single field in an Entity
and how it is to be mapped to JCR structures. 

A mapped field is a basic Java field:

* With a public getter and setter
* Getter and setter named after Java Beans naming convention, for example if the field is called ``foo`` the getter and setter are ``getFoo()`` and ``setFoo()``.
* No argument getter, single argument setter.
* Optionally annotated with the ``org.om.core.api.annotation.Mapped`` annotation
* Annotated with one of the following annotations: ``org.om.core.api.annotation.Property``, ``org.om.core.api.annotation.Collection``, or ``org.om.core.api.annotation.Id``


	@Entity
	public class MyPojo {
	
		@Id
		private String id;
		
		@Property
		private int someValue;
		
		public String getId() {
			return id;
		}
		
		public int getSomeValue() {
			return someValue;
		}
	
	}

Session
-------

The Session is the hub of all Object Mapper operations. It acts as a factory that creates entities for a given identifier. Sessions 
are meant to be cheap and easy to create and it's recommended to create sessions on a per unit of work basis, for example per request.

SessionFactory
--------------

The session factory is, well, a factory for session objects. A session factory holds many high level objects and as such is heavier to create 
than a session. Ideally there should be only one session factory per application.  

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
	
Current Limitations
===================

Method Level Intercepts
-----------------------

Only method level access will be intercepted and mapped to the actual persistence backend. The implemented ProxyFactory uses cglib 
to generate dynamic proxies. Cglib only allows for method level intercept and not field level. That means directly accessing a mapped
field will not return anything as of now. To obtain a mapped property, the appropriate getter has to be used.

As soon as the core stabilizes I'll implement a ProxyFactory using a different bytecode library, maybe Javassist, that allows for field
level access. 

Debug Support for Mappings
--------------------------

Currently there's no good way to test how a mapping will translate into JCR structure. 

Poor Documentation
------------------

Documentation is still in a poor state.

Performance
-----------

I haven't done any benchmarking, but currently there's some overhead in terms of how often objects are created and how mappings are
read over and over again. There's no caching layer whatsoever, so it might be worth investigating how to integrate with various
cache layers. Here we need to think how to seamlessly configure caching with the annotations, cache eviction strategies, etc etc. 

Better Collection Support
-------------------------

Currently we only support java.util.List and java.util.Set. It would be nice to support Maps and clean up the collection code. 

Write Support
-------------

Currently we have read-only support. Adding write support opens up a whole sleigh of new challenges, including, but not limited to: 
transactions, rollbacks, permissions, dirty checking, different types of proxies, etc etc. Once I feel I have a stable core that 
supports reading, I'll start thinking about these things.  

OSGI Support
------------

All modules produce valid and runnable OSGI bundles and there's an OSGI specific bundle that exports a session factory as an OSGI
service. However, due to the more restrictive class loading, there's still issues with incorrect class references. 

The current implementation works fine, but from time to time you'll have to manually refresh its packages.


Changelong
==========

Version 0.1.0
-------------

Added support for mapping ``java.util.Map``s.
