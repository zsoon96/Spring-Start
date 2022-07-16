package hello.springstart.repository;

import hello.springstart.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 하나의 테스트가 끝나면 repository를 비워줘야함 -> 테스트 실행 순서가 무작위이기 때문에 의도치 않은 에러 발생
   @AfterEach
   public void afterEach() {
       repository.clearStore();
   }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("soon");

        // member 데이터 저장
        repository.save(member);

        // repository에 데이터가 잘 들어갔는지 확인
        // 반환 타입이 Optional이기 때문에 get() 사용해서 꺼냄
        Member result = repository.findById(member.getId()).get();

        // member에 저장한 값과 repository에서 꺼낸 값이 같은지 검증
        // 검증 메소드 1 - jupiter.api의 Assertions.assertEquals(expect, actual)
        assertEquals(member, result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("soon1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("soon2");
        repository.save(member2);

        Member result = repository.findByName("soon1").get();

        // 검증 메소드 2 - core.api의 Assertions.assertThat(expect).isEqualTo(actual) => 코드가 더 직관적이라 실무에서 주로 사용
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("soon1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("soon2");
        repository.save(member2);

        List <Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
