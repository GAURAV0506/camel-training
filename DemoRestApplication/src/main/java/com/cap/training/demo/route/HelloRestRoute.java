package com.cap.training.demo.route;

import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * Route to exposing REST endpoints.
 *
 * @author Sanjay Kumar
 */
@Component(value = "helloRestRoute")
public class HelloRestRoute extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        //See link {http://camel.apache.org/rest-dsl.html and http://camel.apache.org/servlet.html}
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
                // and output using pretty print
                .dataFormatProperty("prettyPrint", "true")
                .dataFormatProperty("json.in.disableFeatures", "FAIL_ON_UNKNOWN_PROPERTIES")
                .contextPath("/").port("8080");

        //Rest endpoint for the enquiry office. Consumes and produce Json data.
        rest("/api/v1").description("URI endpoint for Hello World Rest service")
                .id("restRoute")
                .consumes(MediaType.APPLICATION_JSON).produces(MediaType.APPLICATION_JSON)
                .get("/helloWorld")
                    .description("Get Enquiry offices using a set of query parameters.")
                    .outType(String.class)
                    .to("direct:helloWorldRoute");

        from("direct:helloWorldRoute")
                .setBody(constant("{hello-World}"));

    }
}
