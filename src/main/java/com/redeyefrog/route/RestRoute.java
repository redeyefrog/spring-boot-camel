package com.redeyefrog.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class RestRoute extends RouteBuilder {

    @Value("${mock.backend.url}")
    private String backendUrl;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);

        onException(Exception.class)
                .handled(true)
                .process("exceptionProcessor")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.BAD_REQUEST.value()))
                .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
                .setBody(simple("${body}"));

        rest("/qoo")
                .post()
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .routeId("rest")
                .process("Json2Xml")
                .to(backendUrl + "?bridgeEndpoint=true&clientConnectionManager=#clientConnectionManager&connectTimeout=30000")
                .process("Xml2Json");
    }

}
