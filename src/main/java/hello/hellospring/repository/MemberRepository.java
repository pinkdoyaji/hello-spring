package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
* 데이터 저장하는 공간
* */

public interface MemberRepository {
    // optional은 java8에 등장 : 없으면 null 반환하지 않고 optional로 반환
    // null이 될 가능성을 가진 값을 객체로 감싸는 래퍼 클래스
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
