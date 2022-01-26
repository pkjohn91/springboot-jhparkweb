package com.pkjohn.jhparkweb.springboot.service.posts;

import com.pkjohn.jhparkweb.springboot.domain.posts.Posts;
import com.pkjohn.jhparkweb.springboot.domain.posts.PostsRepository;
import com.pkjohn.jhparkweb.springboot.web.dto.PostsListResponseDto;
import com.pkjohn.jhparkweb.springboot.web.dto.PostsResponseDto;
import com.pkjohn.jhparkweb.springboot.web.dto.PostsSaveRequestDto;
import com.pkjohn.jhparkweb.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // 생성자를 수정할 때, 의존성 관계가 변경될 때마다 생성자 코드를 계속 수정해야하는 번거로움 해결
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
        /*
            여기서 보면 쿼리를 날리는 부분이 없다, 가능한 이유는 jpa 영속성 컨텍스트 때문이다.
            영속성 컨텍스트란 엔티티를 영구 저장하는 환경이다. jpa의 핵심내용은 엔티티가 영속성 컨텍스트의 유무에 따라 갈린다.
            jpa의 엔티티 매니저가 활성화된 상태(spring data jpa를 쓴다면 기본 옵션)로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
            이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다. 즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다. 이 개념을 더티체킹이라고 한다.
        */
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되, 조회 기능만 남김, 속도 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        //postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
        /*
            JpaRepository에서 이미 delete 메소드를 지원하고 있음.
            엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있음
            존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제함.
        */
    }
}
