package com.pjh.aed.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjh.aed.exception.RequestDataParsingException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiValueMapConverter {
    public static MultiValueMap<String, String> convert(Object dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
            for (String s : map.keySet()) {
                if(map.get(s) == null)
                    continue;

                params.add(s, map.get(s));
            }
            return params;
        } catch (Exception e) {
            throw new RequestDataParsingException();
        }
    }
}
