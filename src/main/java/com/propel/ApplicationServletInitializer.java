package com.propel;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * Run the Application through tomcat
 */
@Configuration
public class ApplicationServletInitializer extends SpringBootServletInitializer {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.out.println("configure");
        return application.sources(Application.class);
    }
}
