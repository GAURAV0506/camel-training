package com.cap.training.demo;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Main class for spring boot application
 *
 * @author Sanjay Kumar
 */
@SpringBootApplication
public class DemoRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRestApplication.class, args);
    }

    /**
     * Registers Camel HTTP Servlet with Spring application context. See link {http://camel.apache.org/rest-dsl.html and http://camel.apache.org/servlet.html}
     *
     * @return the Servlet registration bean for Camel HTTP servlet.
     */
    @Bean
    public ServletRegistrationBean camelHttpServletRegistration() {

        final CamelHttpTransportServlet camelHttpServlet = new CamelHttpTransportServlet();
        final ServletRegistrationBean servletRegistration = new ServletRegistrationBean(camelHttpServlet, "/*");
        servletRegistration.setName("CamelServlet");


        return servletRegistration;
    }
}
