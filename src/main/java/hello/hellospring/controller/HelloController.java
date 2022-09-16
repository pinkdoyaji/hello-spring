package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // GET/POST의미의 GET이다.
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello 페이지의 spring");
        return "hello";
    }
}
