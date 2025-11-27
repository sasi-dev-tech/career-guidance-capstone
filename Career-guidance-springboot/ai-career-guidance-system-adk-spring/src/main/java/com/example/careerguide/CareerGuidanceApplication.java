/*package com.example.careerguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CareerGuidanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareerGuidanceApplication.class, args);
    }
}*/



package com.example.careerguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.careerguide")
public class CareerGuidanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareerGuidanceApplication.class, args);
    }
}

