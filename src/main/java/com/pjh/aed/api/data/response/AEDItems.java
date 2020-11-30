package com.pjh.aed.api.data.response;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "body")
@Setter
public class AEDItems {
    private List<AEDItemDto> items;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    public List<AEDItemDto> getItems(){
        return items;
    }
}
