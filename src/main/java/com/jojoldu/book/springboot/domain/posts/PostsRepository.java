package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Tip.
 * Entity 클래스와 기본 Entity Repository 는 함께 위치해야 한다.
 * 프로젝트가 커질 경우에는 같은 도메인에서 관리한다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
