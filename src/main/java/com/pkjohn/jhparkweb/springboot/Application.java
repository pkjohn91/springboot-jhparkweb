package com.pkjohn.jhparkweb.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // jpa auditing 어노테이션들을 모두 활성화할 수 있도록 함.
@SpringBootApplication //스프링부트의 자동설정, 스프링 Bean 읽기와 생성 자동설정,
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
