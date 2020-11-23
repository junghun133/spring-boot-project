package com.pjh.aed.service.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

@Service
public class AutowirdService {

	private  final AutowireCapableBeanFactory beanFactory;
	
	@Autowired
	public AutowirdService(AutowireCapableBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	public ServiceRunnerInterface getBean(Class<?>  clz) {
		ServiceRunnerInterface bean = null;
		try {
			bean = (ServiceRunnerInterface) beanFactory.getBean(clz);
		} catch (Exception e) {
			throw new IllegalArgumentException("Can't Find  " + clz.getName(), e);
		}
		return bean;
	}
	
	public ServiceRunnerInterface createBean(Class<?>  clz) {
		Object bean = null;
		try {
//			Class<?> beanClass = Class.forName(clz.getName());
			bean = beanFactory.createBean(clz, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
			beanFactory.initializeBean(bean, clz.getSimpleName());
		} catch (Exception e) {
			throw new IllegalArgumentException("Can't Create  " + clz.getName(), e);
		}
		return (ServiceRunnerInterface) bean;
	}
}
