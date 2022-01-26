package com.pkjohn.jhparkweb.springboot.web.dto;

import com.pkjohn.jhparkweb.springboot.domain.posts.Posts;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(@NotNull Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
