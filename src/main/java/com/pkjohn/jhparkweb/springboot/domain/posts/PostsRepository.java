package com.pkjohn.jhparkweb.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// ibatis나 mybatis에서 DAO라고 불리는 DB layer 접근자이다. jpa에서는 repository라고 부른다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
    /*
        SpringDataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도됌
        실제 앞 코드는 SpringDataJpa에서 제공하는 기본 메소드만으로 해결 가능하긴하다.
        다만, @Query가 훨씬 가독성이 좋으니 선택해서 사용하자.
    */
}
