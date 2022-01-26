package com.pkjohn.jhparkweb.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //스프링부트의 자동설정, 스프링 Bean 읽기와 생성 자동설정,
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
