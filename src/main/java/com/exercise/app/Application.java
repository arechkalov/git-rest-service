package com.exercise.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by arecicalov on 2/20/2017.
 */
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.exercise*")
public class Application {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx
                = SpringApplication.run(Application.class, args);
    }
}
