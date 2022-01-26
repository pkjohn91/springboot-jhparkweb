package com.pkjohn.jhparkweb.springboot.service.posts;

import com.pkjohn.jhparkweb.springboot.domain.posts.PostsRepository;
import com.pkjohn.jhparkweb.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // 생성자를 수정할 때, 의존성 관계가 변경될 때마다 생성자 코드를 계속 수정해야하는 번거로움 해결
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
