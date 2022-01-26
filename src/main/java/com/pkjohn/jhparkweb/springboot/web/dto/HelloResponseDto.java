package com.pkjohn.jhparkweb.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //선언된 모든 final 필드가 포함된 생성자를 생성, final이 없는 필드는 생성자에 포함X
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
