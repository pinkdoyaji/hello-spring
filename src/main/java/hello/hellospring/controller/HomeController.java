package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
* 이 페이지는 회원등록 홈페이지입니다.
* */
@Controller
public class HomeController {

    /* 기본적으로 static 안에 있는 index.html이 나올 수 잇지만
    *  @GetMapping("/") 이라고 했을때 Controller 있는지 찾고 파일 있는지 찾는거니까
    * 근데 지금 Controller에 있네? 그러면 index.html 찾지 않고 여기서 설정한 "home" 찾겠지?!
    * */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
