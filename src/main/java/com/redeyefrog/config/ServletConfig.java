package com.redeyefrog.config;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean redServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/red/*");

        bean.setName("CamelServlet");

        return bean;
    }

}
