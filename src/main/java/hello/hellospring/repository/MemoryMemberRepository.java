package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 해당 repository 테스트는 MemoryMemberRepositoryTest이다.
//@Repository
public class MemoryMemberRepository implements MemberRepository{

    // == 동시성 문제를 고려하여 실무에서는 다음과 같이 쓰진 않음 ==
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    // =======================================================

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        // null임을 반환함을 수도 있음
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // store의 값을 돌려
                .filter(member -> member.getName().equals(name)) // 뽑아온 값의 이름이 찾는 이름과 같아?
                .findAny(); // 조건에 일치하는 요소 리턴해줘
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
