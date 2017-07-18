package com.cap.training.demo.route;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.springframework.stereotype.Component;

/**
 * @author Sanjay Kumar
 */
@Component(value = "helloWorldRoute")
public class HelloWorldRoute  extends RouteBuilder{
    @Override
    public void configure() throws Exception {

        from("file:./resources/file?noop=true") //noop =true, the file is not moved or deleted in any way
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        GenericFile body = exchange.getIn().getBody(GenericFile.class);
                        File file = (File) body.getFile();

                        Files.lines(Paths.get(file.getAbsolutePath())).forEach(s -> System.out.println(s.toString()));
                    }
                });
    }
}
