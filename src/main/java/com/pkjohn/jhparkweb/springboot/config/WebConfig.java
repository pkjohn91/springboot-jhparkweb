package com.pkjohn.jhparkweb.springboot.config;
import lombok.RequiredArgsConstructor;
import com.pkjohn.jhparkweb.springboot.config.auth.LoginUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/*
    LoginUserArgumentResolver가 스프링에서 인식될 수 있도록 WebMvcConfigurer에 추가하는 클래스
    HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가해야 한다.
    다른 HandlerMethodArgumentResolver가 필요하다면 같은 방식으로 추가하면 됌
 */
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers (List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
