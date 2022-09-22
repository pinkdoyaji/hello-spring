package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 강의에서는 여기다 service를 구현했다.
//@Service
public class MemberService {

    // =========== 이렇게 DI 작업해두는게 정형화되어있음 =========================
    private final MemberRepository memberRepository;

    // memberService 입장에서 내가 new 하지않고
    // 외부에서 넣어준다. 이걸 DI라고 한다.
    // 생성자 주입
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // ========================================================================

    /*
    * 회원가입
    * */
    public Long join(Member member) {

        // 같은 회원이 있으면 존재한다고 해야함
        validateDuplicateMember(member);

        // 저장
        memberRepository.save(member);
        return member.getId();
    }

    /*
    * 중복 회원 체크
    * */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(r -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
     * 회원 조회
     * */
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
