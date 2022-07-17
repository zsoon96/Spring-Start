package hello.springstart.repository;

import hello.springstart.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    // 메모리에 데이터를 저장하기 위해
    private static Map<Long, Member> store = new HashMap<>();
    // key값을 생성해주기 위해
    private static long sequence = 0L;

    // 정보 저장
    @Override
    public Member save(Member member) {
        // 저장할 때, 시퀀스 값(ID) + 1
        member.setId(++sequence);
        // 해당 ID와 멤버 정보 담아주기
        store.put(member.getId(), member);
        return member;
    }

    // id 값으로 정보 조회
    @Override
    public Optional<Member> findById(Long id) {
        // id 값으로 찾고자하는 데이터가 없을 경우, NPE 문제를 방지하기 위해 Optional.ofNullable 사
        return Optional.ofNullable(store.get(id));
    }

    // name 값으로 정보 조회
    @Override
    public Optional<Member> findByName(String name) {
        // 람다를 사용하여 반복문
        return store.values().stream()
                // 저장되어 있는 member의 name과 파라미터로 받은 name이 일치한 값 찾기
                .filter(member -> member.getName().equals(name))
                // 찾는 정보가 하나라도 있으면 반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // map 자료구조로 저장되어 있는 데이터를 value 값(member 객체)만 새로운 리스트에 담아서 반환
        return new ArrayList<>(store.values());
    }

    // repository 데이터 비워주기
    public void clearStore() {
        store.clear();
    }
}
