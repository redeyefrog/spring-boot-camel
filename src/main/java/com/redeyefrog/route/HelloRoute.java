package com.redeyefrog.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HelloRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        Map<String, String> params = new HashMap<>();

        params.put("hello","world");

        from("direct:hello").transform().constant(new ObjectMapper().writeValueAsString(params));
    }

}
