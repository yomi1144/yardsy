package com.yardsy.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class YardsyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(YardsyApplication.class, args);

        String currentAppName = context.getEnvironment().getProperty("spring.application.name");
        System.out.println("Application Name: ➡ " + currentAppName);
    }

}
