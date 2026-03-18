package com.tss.DynamicDI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class DynamicDI {
    @SpringBootApplication
    public static class DynamicDiApplication {

        public static void main(String[] args) {
            SpringApplication.run(DynamicDiApplication.class, args);
        }

    }
}
