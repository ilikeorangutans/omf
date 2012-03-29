package org.om.dao.annotation.transactional.filter;

import net.sf.cglib.proxy.Enhancer;

import com.khubla.simpleioc.IOCBeanRegistry;
import com.khubla.simpleioc.exception.IOCException;
import com.khubla.simpleioc.filter.IOCInstantiationFilter;
import com.khubla.simpleioc.xml.Bean;

public class TransactionalIOCInstantiationFilter implements IOCInstantiationFilter {

	public Object filter(IOCBeanRegistry iocBeanRegistry, Object object, Bean bean) throws IOCException {
		try {
			Enhancer e = new Enhancer();
			e.setSuperclass(object.getClass());
			e.setCallback(new JCRTransactionalIterceptor(object));
			return e.create();
		} catch (final Exception e) {
			throw new IOCException("Exception in filter", e);
		}
	}
}
