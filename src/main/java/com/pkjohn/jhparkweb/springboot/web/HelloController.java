package com.pkjohn.jhparkweb.springboot.web;

import com.pkjohn.jhparkweb.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 json을 반환하는 컨트롤러로 만들어줌, @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용하는 역할?
public class HelloController {

    @GetMapping("/hello") //HTTP method인 Get의 요청을 받을 수 있는 api 어노테이션, @RequestMapping(method = RequestMethod.GET)과 같은 의미
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
