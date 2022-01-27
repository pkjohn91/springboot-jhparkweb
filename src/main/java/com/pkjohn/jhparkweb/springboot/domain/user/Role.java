package com.pkjohn.jhparkweb.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//각 사용자의 권한을 관리할 enum 클래스
@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반사용자");

    private final String key;
    private final String title;
    /*
        스프링 시큐리티에서는 권한 코드에 항상 ROLE_이 앞에 있어야만 한다.
        그래서 코드별 키 값 앞에 ROLE_을 붙였다.
     */
}
