package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(SessionUser.class);

    private String name;
    private String email;
    private String picture;

    /*
     * SessionUser 에는 인증된 사용자 정보만 필요합니다.(name, email, picture 만)
     */
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
