package com.pkjohn.jhparkweb.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 스프링부트 2. 버전에서는 GenerationType.IDENTITY를 추가해야만 auto_increment가 됌
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    //자바빈 규약을 생각하며 getter/setter를 무작정 생성하는 경우가 있는데, 이렇게 되면 인스턴스 값들이 언제 어디서 변해야하는 지 구분하기 어렵다.
    //Entity클래스에서는 절대 setter 메소드를 만들지 않는다. 대신 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해라.
}
