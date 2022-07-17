package hello.springstart;


import hello.springstart.repository.MemberRepository;
import hello.springstart.repository.MemoryMemberRepository;
import hello.springstart.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Bean 등록 방법 2 - 자바 코드로 직접 스프링 빈 등록하는 방식
// -> 컴포넌트 스캔 방식에 비해 쳐야할 코드는 많지만,
// -> 변경 사항이 있을 경우, 해당 설정파일에서만 바꿔주면 되기 때문에 상대적으로 유지보수가 쉽다.
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // Bean 등록 2가지 방법에 대한 쓰임 정리 !!!
    // 실무에서는 주로 정형화 된 Controller / Service / Repository와 같은 코드는 '컴포넌트 스캔 방식' 사용
    // 정형화 되지 않고, 상황에 따라 구현 클래스를 변경해야하면 '자바 코드로 직접 스프링 빈에 등록 방식' 사용
}
