package com.neu.dreambuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DreamBuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamBuilderApplication.class, args);
    }

}
