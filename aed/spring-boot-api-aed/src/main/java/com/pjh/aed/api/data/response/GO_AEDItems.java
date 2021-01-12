package com.pjh.aed.api.data.response;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "body")
@Setter
@Getter
public class GO_AEDItems {
    private List<GO_AEDItemDto> items;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    public List<GO_AEDItemDto> getItems(){
        return items;
    }
    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;

}
