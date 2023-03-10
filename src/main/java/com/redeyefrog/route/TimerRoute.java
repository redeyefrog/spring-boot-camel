package com.redeyefrog.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:fooTimer?period=10m")
                .routeId("timer")
                .log("Fire Time: ${header.firedTime}");
    }

}
