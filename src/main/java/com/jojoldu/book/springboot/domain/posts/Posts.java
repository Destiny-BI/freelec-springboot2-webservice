package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Tip 1.
 * Entity 클래스에서는 절대 Setter 메소드를 만들지 않습니다.
 * 필드의 값을 변경하고자 한다면 명확히 그 목적과 의도를 가지는 메소드를 추가해서 사용한다.
 *
 * Tip 2.
 * 생성자 대신 Builder를 사용한다면 객체 생성 시 명확한 인자를 줄 수 있습니다.
 */
/*
 * @Getter
 * 클래스 내 모든 필드의 Getter 메소드를 자동생성
 */
@Getter
/*
 * @NoArgsConstructor
 * 기본 생성자 자동 추가
 * public Posts() {}와 같은 효과
 */
@NoArgsConstructor
/*
 * @Entity
 * 테이블과 링크될 클래스임을 나타냅니다.
 * 언더스코어 네이밍으로 테이블 이름을 매칭합니다.
 */
@Entity
public class Posts extends BaseTimeEntity {
    /*
     * @Id
     * 해당 테이블의 PK 필드를 나타냅니다.
     */
    @Id
    /*
     * @GeneratedValue
     * PK의 생성 규칙을 나타냅니다.
     * 스프링 부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment 가 됩니다.
     * ※ 주민등록번호, 복합키 등은 유니크 키로 별도로 추가하시는 것을 추천.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * @Column
     * 테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됩니다.
     * 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다.
     * 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex:title), 타입을 TEXT로 변경하고 싶거나(ex:content)
     * 등의 경우에 사용합니다.
     */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /*
     * @Builder
     * 해당 클래스의 빌더 패턴 클래스를 생성
     * 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
     */
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
