package com.pkjohn.jhparkweb.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class) //스프링부트테스트와 JUnit 사이에 연결자 역할, JUnit에 내장된 실행자 외에 다른 실행자 실행
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC)에 집중할 수 있는 어노테이션, 선언 시 @Controller, @ControllerAdvice등을 사용가능, @Service, @Component, @Repository 사용 못함, 여기선 컨트롤러만 사용하기때문에 선언
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 웹 api를 테스트할 때 사용, 스프링mvc 테스트의 시작점, 이 클래스를 통해 http get, post 등에 대한 api 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 http get요청을 함
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증, htt Header의 status를 검증, 200/404/500 등의 상태를 검증, 여기선 OK or 200인지 검증
                .andExpect(content().string(hello)); //응답 본문의 내용을 검증, Controller에서 "hello"를 리턴하기 때문에 값이 맞는 지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto)")
                .param("name", name)
                .param("amount", String.valueOf(amount))) //param은 문자열만 받는다. 따라서 valueOf로 문자열로 전환
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //json 응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
