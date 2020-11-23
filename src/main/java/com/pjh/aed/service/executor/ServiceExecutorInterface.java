package com.pjh.aed.service.executor;


public interface ServiceExecutorInterface<T> {
	public T execute(ServiceRequest request);
}
