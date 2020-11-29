package com.pjh.aed.api.data.response;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Setter @Getter
@XmlRootElement(name ="item")
@XmlAccessorType(XmlAccessType.FIELD)
public class AEDDto {
    @XmlElement(name ="buildAddress" )
    private String buildAddress;

    @XmlElement(name ="buildPlace" )
    private String buildPlace;

    @XmlElement(name ="clerkTel" )
    private String clerkTel;

    @XmlElement(name ="manager" )
    private String manager;

    @XmlElement(name ="managerTel" )
    private String managerTel;

    @XmlElement(name ="mfg" )
    private String mfg;

    @XmlElement(name ="model" )
    private String model;

    @XmlElement(name ="org" )
    private String org;

    @XmlElement(name ="rnum" )
    private String rnum;

    @XmlElement(name ="wgs84Lat" )
    private String wgs84Lat;

    @XmlElement(name ="wgs84Lon" )
    private String wgs84Lon;

    @XmlElement(name ="zipcode1" )
    private String zipcode1;

    @XmlElement(name ="zipcode2" )
    private String zipcode2;
}
