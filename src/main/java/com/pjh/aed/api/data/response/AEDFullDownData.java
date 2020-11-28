package com.pjh.aed.api.data.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class AEDFullDownData {
    private Body body;

    @XmlRootElement(name = "body")
    private static class Body {

    }

}
