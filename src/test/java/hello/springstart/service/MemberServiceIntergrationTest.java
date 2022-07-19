package hello.springstart.service;

// 아래와 같은 통합 테스트보다는 이전 방식의 '단위 테스트'를 잘 만드는게 더욱 좋은 테스트라고 한다!

import hello.springstart.domain.Member;
import hello.springstart.repository.MemberRepository;
import hello.springstart.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 컨테이너와 테스트를 함께 실행하는 어노테이션
@SpringBootTest
// 테스트 케이스에 트랜잭션 어노테이션을 사용하면,
// 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후외 항상 콜백해주기 때문에
// DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않아 반복해서 돌릴수 있음 !
@Transactional
class MemberServiceIntergrationTest {

    // 기존 비즈니스 로직에서의 DI는 생성자로 해주는게 좋지만,
    // 테스트 케이스 로직에서는 편한 방법으로 사용해도 무방 !
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    // 회원가입 정상 플로우
    @Test
    // @Commit // DB 반영 테스트
    void 회원가입() {
        // given (입력)
        Member member = new Member();
        member.setName("soon1");

        // when (실행)
        Long saveId = memberService.join(member);

        // then (결과)
        Member result = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(result.getName());
    }

    // 회원가입 예외 플로우 -> 매우 중요
    @Test
    public void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("soon1");

        Member member2 = new Member();
        member2.setName("soon1");

        // when
        memberService.join(member1);
        // memberService.join(member2)를 실행했을 때, 예외가 발생하는지 검증
        // assertThrows 자체가 메세지를 반환
        assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 예외 발생


    }

    @Test
    void 모든정보조회() {
        // given
        Member member1 = new Member();
        member1.setName("soon1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("soon2");
        memberRepository.save(member2);

        // when
        List<Member> result = memberService.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    // 테스트 통과 안되는 이유?
    // 결과에서는 메모리 상의 위치 값이 다르게 나옴.... > 단위 테스트에서는 되는데...
    @Test
    void 단일정보조회() {
        // given
        Member member1 = new Member();
        member1.setName("soon1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("soon2");
        memberRepository.save(member2);

        // when
        Member result = memberService.findOne(member1.getId()).get();

        // then
        assertThat(member1).isEqualTo(result);
    }
}