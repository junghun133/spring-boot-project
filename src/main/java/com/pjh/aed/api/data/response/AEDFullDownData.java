package com.pjh.aed.api.data.response;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement(name = "response")
@Data
public class AEDFullDownData {
        private Map<String, String> header;

//        private List<AEDDto> aedDtos;
//
//        @XmlElementWrapper(name = "items")
//        @XmlElement(name = "item")
//        public List<AEDDto> getAedDtos() {
//            return aedDtos;
//        }
}
