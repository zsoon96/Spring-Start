package hello.springstart.repository;

import hello.springstart.domain.Member;

import java.util.List;
import java.util.Optional;

// 저장소가 선정되어있지 않은 가상의 시나리오로
// 인터페이스를 만든 후, 구현체를 통해 정보 조회
public interface MemberRepository {
    // 회원 정보 저장
    Member save(Member member);
    // id로 회원 정보 조회
    Optional<Member> findById(Long id);
    // name으로 회원 정보 조회
    Optional<Member> findByName(String name);
    // Member 전체 조회
    List<Member> findAll();
}
