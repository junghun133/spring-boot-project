package com.pjh.aed.http;

import com.pjh.aed.exception.APIURISyntaxException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;

@AllArgsConstructor
@Setter @Getter
@Slf4j
public class URIAssemble {
    String baseUrl;
    String apiKey;

    public URI basicAEDURIAddress(){
        URI uri = null;
        try {
            uri = new URI(baseUrl + "?serviceKey=" + apiKey);
        } catch (URISyntaxException e) {
            log.debug("URI SyntaxException.. url:" + baseUrl);
            throw new APIURISyntaxException();
        }

        return uri;
    }
}
