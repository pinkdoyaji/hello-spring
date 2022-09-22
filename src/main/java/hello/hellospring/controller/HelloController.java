package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // GET/POST의미의 GET이다.
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello 페이지의 spring");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "hello", required = false) String name, Model model) {
        /* value="hello"    http://localhost:8080/hello-mvc?hello=doyaji get방식 URL의 Key값
        *  name=            key값이 담길 변수 */
        model.addAttribute("name", name);
        return "helloMvc";
    }

    // JSON 방식으로 내려준다
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloSpring(@RequestParam(value = "name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    public class Hello {
        private String name="";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
