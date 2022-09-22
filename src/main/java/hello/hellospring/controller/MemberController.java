package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
* @Controller 해두면 스프링 컨테이너가 뜰 때 객체 생성해서 스프링이 관리해둔다.
*  service를 new해도 되지만 여기 하나만 그 service를 쓰는게 아니니까 등록시켜두는게 편하겠지
* */
@Controller
public class MemberController {

    private MemberService memberService;

    /* autowired : 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    * 객제 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection)
    * auto(자동) wired(연결)
    * */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 등록화면으로 진입
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 등록 기능
    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());
        memberService.join(member);
        return "redirect:/";
    }

    // 등록화면으로 진입
    @GetMapping("/members")
    public String list(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }
}
