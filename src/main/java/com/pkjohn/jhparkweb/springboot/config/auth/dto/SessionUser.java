package com.pkjohn.jhparkweb.springboot.config.auth.dto;

import com.pkjohn.jhparkweb.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/*
    인증된 사용자 정보만 필요한 클래스
 */
@Getter
/*
    자바 직렬화란 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트(byte) 형태로
    데이터 변환하는 기술과 바이트로 변환된 데이터를 다시 객체로 변환하는 기술(역직렬화)을 의미.
    시스템적으로 이야기하자면 JVM(Java Virtual Machine 이하 JVM)의 메모리에 상주(힙 또는 스택)되어 있는 객체 데이터를 바이트 형태로
    변환하는 기술과 직렬화된 바이트 형태의 데이터를 객체로 변환해서 JVM으로 상주시키는 형태를 의미.
 */
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser (User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
