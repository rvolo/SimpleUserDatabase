package com.simpleuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Sample application demonstrating a crud rest interface and using hibernate to setup the database
 *
 */
@SpringBootApplication
public class SimpleUserApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SimpleUserApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleUserApplication.class, args);
    }

}