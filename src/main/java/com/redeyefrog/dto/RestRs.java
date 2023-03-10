package com.redeyefrog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JacksonXmlRootElement(localName = "XML")
@Data
public class RestRs {

    @JacksonXmlProperty(localName = "status_code")
    @JsonProperty("status_code")
    private String code;

    @JacksonXmlProperty(localName = "status_desc")
    @JsonProperty("status_desc")
    private String desc;

}
