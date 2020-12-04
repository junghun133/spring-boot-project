package com.pjh.aed.http;

import com.pjh.aed.data.request.CommonRequestData;
import com.pjh.aed.exception.APIURISyntaxException;
import com.pjh.aed.service.executor.ServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Slf4j
public class URIAssemble {
    URI uri;

    public URIAssemble basicAEDURIAddress(String baseUrl, String apiKey){
        try {
            uri = new URI(baseUrl + "?serviceKey=" + apiKey);
        } catch (URISyntaxException e) {
            log.debug("URI SyntaxException.. url:" + baseUrl);
            throw new APIURISyntaxException();
        }

        return this;
    }

    public URIAssemble addParam(ServiceRequest serviceRequest){
        CommonRequestData request = serviceRequest.getRequest();
        MultiValueMap<String, String> valueMap = MultiValueMapConverter.convert(request);

        uri = UriComponentsBuilder.fromUriString(getUri().toString()).queryParams(valueMap).build().encode().toUri();
        return this;
    }
}
