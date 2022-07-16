package hello.springstart.service;

import hello.springstart.domain.Member;
import hello.springstart.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // 회원가입 정상 플로우
    @Test
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

        // 위에 구문을 풀어서 작성
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


//        try {
//            memberService.join(member2); // 예외 발생
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

    }

    @Test
    void 모든정보조회() {
        // given

        // when

        // then
    }

    @Test
    void 단일정보조회() {
        // given

        // when

        // then
    }
}