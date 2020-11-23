package com.pjh.aed.service.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceRunnerFactory {
	
	private final AutowirdService autowirdService;
	
	@Autowired
	public ServiceRunnerFactory(AutowirdService autowirdService) {
		this.autowirdService = autowirdService;
	}
	
	public  ServiceRunnerInterface findRunner(Class<?> runnerType) {
		return autowirdService.getBean(runnerType);
	}
	
}
