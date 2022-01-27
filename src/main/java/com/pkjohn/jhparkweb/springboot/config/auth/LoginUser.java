package com.pkjohn.jhparkweb.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    Controller에서 로그인 정보를 url호출 메소드 마다 써주어야 하기 때문에 반복적인 코드가 발생한다.
    따라서 로그인 정보를 가져오는 인터페이스를 따로 만들어 코드의 반복을 줄여줌.
    메소드 인자로 세션값을 바로 받도록 한다.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
/*
    @Target : 어노테이션이 생성될 수 있는 위치를 지정
              여기선 parameter로 지정해서 메소드의 파라미터로 선언된 객체에서만 사용할 수 있음.

    @interface : 이 파일을 어노테이션 클래스로 지정
                 LoginUser라는 이름을 가진 어노테이션이 생성됐다고 보면 됌.
 */
public @interface LoginUser {

}
