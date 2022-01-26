package com.pkjohn.jhparkweb.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
// ibatis나 mybatis에서 DAO라고 불리는 DB layer 접근자이다. jpa에서는 repository라고 부른다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
