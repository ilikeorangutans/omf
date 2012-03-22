
jcrcache is a simple decorator around an arbitrary PersistenceDelegate which caches values.  It is intended to be used int he situation that the JCR is being accessed over RMI, which is very slow. 