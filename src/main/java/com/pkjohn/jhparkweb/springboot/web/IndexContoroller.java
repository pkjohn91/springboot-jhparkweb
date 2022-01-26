package com.pkjohn.jhparkweb.springboot.web;

import com.pkjohn.jhparkweb.springboot.service.posts.PostsService;
import com.pkjohn.jhparkweb.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexContoroller {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
        //머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됌.
        //앞의 경로는 src/main/resources/templates
        //뒤의 경로는 .mustache
        //여기선 index를 반환하므로, src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리됌
        //ViewResolver는 URL 요청의 결과를 전달할 타입과 값을 지정하는 관리자 역할이다.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);

        return "posts-update";
    }
}
