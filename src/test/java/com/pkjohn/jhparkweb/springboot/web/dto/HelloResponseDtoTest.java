package com.pkjohn.jhparkweb.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        //assertj라는 테스트 검증 라이브러리의 검증 메소드, isEqualTo와 같이 메소드를 이어서 사용 가능.
        //isEqualTo는 assertj의 동등비교 메소드, assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공
        //Junit의 assertThat이 아닌 assertj의 assertThat을 사용하였고, Junit에서 자동으로 라이브러리 등록을 해줌.
        //assertj의 장점
        // CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음: Junit의 assertThat을 쓰면 is()와 같이 CoreMatchers라이브러리가 필요하지 않음
        // 자동완성이 좀 더 확실하게 지원됌: IDE에서는 CoreMatchers와 같은 Matcher라이브러리의 자동완성 지원이 약함.
    }
}
