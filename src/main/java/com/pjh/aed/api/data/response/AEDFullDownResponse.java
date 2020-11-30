package com.pjh.aed.api.data.response;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement(name = "response")
@Setter
@Getter
public class AEDFullDownResponse implements AEDResponseData {
        private Map<String, String> header;
        private AEDItems body;
}
