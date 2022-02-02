package com.pkjohn.jhparkweb.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // jpa auditing 어노테이션들을 모두 활성화할 수 있도록 함.
/*
    test할 때 @EnableJpaAuditing가 @SpringBootApplication와 함께 있다보니
    @WebMvcTest에서도 스캔하게 됌. 그래서 @EnableJpaAuditing과 @SpringBootApplication를 분리하기로 함.
 */
@SpringBootApplication //스프링부트의 자동설정, 스프링 Bean 읽기와 생성 자동설정,
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
