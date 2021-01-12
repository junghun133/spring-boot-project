package com.pjh.aed.service.executor;

import com.pjh.aed.data.request.CommonRequestData;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequest {
	@NonNull
	private CommonRequestData request;
	@NonNull
	private Class<?>  clz;
	
	public static ServiceRequest createServiceRequest(CommonRequestData request, Class<?> clz) {
		if (null == request)  request =  new CommonRequestData();
		return ServiceRequest.builder().request(request).clz(clz).build();
	}
}
