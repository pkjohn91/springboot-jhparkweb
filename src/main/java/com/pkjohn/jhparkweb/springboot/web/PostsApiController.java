package com.pkjohn.jhparkweb.springboot.web;

import com.pkjohn.jhparkweb.springboot.service.posts.PostsService;
import com.pkjohn.jhparkweb.springboot.web.dto.PostsResponseDto;
import com.pkjohn.jhparkweb.springboot.web.dto.PostsSaveRequestDto;
import com.pkjohn.jhparkweb.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) { //@PathVariable : url주소에 인자값을 넣어 받을 때 사용.. @RequestParam은 "api/posts=?"등으로 표현될 때 사용한다.
        return postsService.findById(id);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long delete (@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
