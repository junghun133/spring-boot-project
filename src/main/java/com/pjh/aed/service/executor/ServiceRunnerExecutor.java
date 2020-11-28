package com.pjh.aed.service.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceRunnerExecutor implements ServiceExecutorInterface<String> {

	private final ServiceRunnerFactory serviceRunnerFactory;
	
	@Autowired
	 public ServiceRunnerExecutor( ServiceRunnerFactory serviceRunnerFactory) {
		 this.serviceRunnerFactory = serviceRunnerFactory;
	}
	@Override
	public String execute(ServiceRequest request) {

		return serviceRunnerFactory.findRunner(request.getClz()).runService(request);
	}
}
