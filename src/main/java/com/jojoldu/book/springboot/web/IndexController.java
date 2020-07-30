package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    /*
     * Model
     * - 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
     * - 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache 에 전달합니다.
     */
    @GetMapping("/")
    public String index(Model model) {
        /*
         * 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정.
         * 앞의 경로는 src/main/resources/templates, 뒤의 파일 확장자는 .mustache 가 붙는다.
         * 즉, src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리된다.
         */
        model.addAttribute("posts", postsService.findAllDesc());

        /*
         * (SessionUser) httpSession.getAttribute("user")
         * - 앞서 작성된 CustomOAuth2UserService 에서 로그인 성공 시 세션에 SessionUser 를 저장하도록 구성.
         * - 즉, 로그인 성공 시 httpSession.getAttribute("user") 에서 값을 가져올 수 있습니다.
         */
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        /*
         * if (user != null)
         * - 세션에 저장된 값이 있을 때만 model에 userName 으로 등록합니다.
         * - 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 됩니다.
         */
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
