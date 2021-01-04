package com.pjh.web.shop.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjh.web.shop.exception.InternalServerException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ProductModifyHistoryManager {
    private final ObjectMapper objectMapper;

    public String convertPojo2Json(List<String> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new InternalServerException(e);
        }
    }

    public List<String> getDifference(Object s1, Object s2) throws IllegalAccessException {
        List<String> values = new ArrayList<>();
        for (Field field : s1.getClass().getSuperclass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value1 = field.get(s1);
            Object value2 = field.get(s2);
            if (value1 != null && value2 != null) {
                if (!Objects.equals(value1, value2)) {
                    values.add(String.valueOf(field.getName()+": "+value1+" -> "+value2));
                }
            }
        }
        return values;
    }
}
