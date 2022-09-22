package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 테스트 끝날때마다 깔끔하게 지워줘야 한다.
    /*
    *   한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 이렇게
        되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다. @AfterEach 를 사용하면 각 테스트가
        종료될 때 마다 이 기능을 실행한다. 여기서는 메모리 DB에 저장된 데이터를 삭제한다
    * */
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void save() {

        // 1. 이름 세팅했다.
        Member member = new Member();
        member.setName("doyaji");

        // 2. 개발한 repository 확인한다
        memberRepository.save(member);

        // 3. 제대로 세팅되었는지 확인한다
        Member result = memberRepository.findById(member.getId()).get();

        // 4. member 안에 있는 객체와, 수행후 저장된 객체가 같아?
        //Assertions.assertEquals(member, null);
        // 요새는 이걸 많이 쓴다.
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("doyaji");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("pengsoo");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("pengsoo").get();
        Assertions.assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("doyaji");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("pengsoo");
        memberRepository.save(member2);

       List<Member> list = memberRepository.findAll();
       Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void findNullName() {
        Member member = new Member();
        member.setName("doyaji");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("pengsoo");
        memberRepository.save(member2);

        memberRepository.findByName("doyaji")
                        .ifPresent( r -> {
                            System.out.println("존재한다.");
                        });
    }
}
