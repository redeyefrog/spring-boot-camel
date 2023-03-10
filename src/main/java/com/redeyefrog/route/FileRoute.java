package com.redeyefrog.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:C://test1?noop=true")
                .routeId("file")
                .to("file:C://test2");
    }

}
