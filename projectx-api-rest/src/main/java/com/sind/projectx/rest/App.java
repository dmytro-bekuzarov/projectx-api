package com.sind.projectx.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Dmytro_Bekuzarov
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.sind.projectx")
@EnableMongoRepositories(basePackages = "com.sind.projectx.repository")
public class App {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}