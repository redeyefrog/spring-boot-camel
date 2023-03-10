package com.redeyefrog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JacksonXmlRootElement(localName = "XML")
@Data
public class RestRq {

    @JacksonXmlProperty(localName = "MESSAGE")
    @JsonProperty("message")
    private String message;

}
