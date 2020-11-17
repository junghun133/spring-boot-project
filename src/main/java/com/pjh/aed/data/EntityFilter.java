package com.pjh.aed.data;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Service;

@Service
public class EntityFilter {

    public FilterProvider filter(String filterName, String ... items) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept(
                        items
                );
        return new SimpleFilterProvider().addFilter(filterName, filter);
    }
}
