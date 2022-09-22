package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;

    // clear 시켜주기 위하여 그런데 지금 memberService랑 다른 객체니까
    MemoryMemberRepository memoryMemberRepository;

    // 각 테스트 메서드 실행 전에 호출
    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void clearStore() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void join() {

        // given (무언가를 전달했을 때)
        Member member = new Member();
        member.setName("doyaji");

        // when (무언가를 실행했을 때)
        Long memberId = memberService.join(member);

        // then (결과는 이렇게 나와야해)
        Optional<Member> result = memberService.findOne(memberId);
       // Assertions.assertThat(result.get().getName().equals(member.getName()));
    }

    // 테스트는 예외케이스가 중요하다.
    // join의 예외케이스는 중복체크가 잘 되고 잇는지가 중요
    @Test
    public void validateJoin() {

        // given (무언가를 전달했을 때)
        Member member = new Member();
        member.setName("doyaji");

        // when (무언가를 실행했을 때)
        Long memberId = memberService.join(member);

        Member member1 = new Member();
        member1.setName("doyaji");

        //Long memberId1 = memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        assertThat(e.getMessage());

        // then (결과는 이렇게 나와야해)
    }
}
