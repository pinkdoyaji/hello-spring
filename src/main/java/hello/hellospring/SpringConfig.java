package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 컴포넌트 스캔( @Service, @Repository ...)를 하지 않고 직접 빈으로 등록하는 방법
* 다만 Controller는 그대로 둔다.
* XML로 설정하는 방식도 있지만 최근에는 잘 사용하지 않으므로 생략한다.
* */
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
