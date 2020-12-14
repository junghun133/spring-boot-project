package com.pjh.test.daou.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjh.test.daou.domain.ProductMaster;
import com.pjh.test.daou.exception.InternalServerException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductHistoryManager {
    private final ObjectMapper objectMapper;

/*
    public Error convertJson2Pojo(String errorJson) throws IOException {
        return objectMapper.readValue(errorJson, Error.class);
    }*/

    public String convertPojo2Json(ProductMaster productMaster) {
        try {
            return objectMapper.writeValueAsString(productMaster);
        } catch (JsonProcessingException e) {
            throw new InternalServerException(e);
        }
    }
}
