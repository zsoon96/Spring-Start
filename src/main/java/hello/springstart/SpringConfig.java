package hello.springstart;


import hello.springstart.aop.TimeTraceAop;
import hello.springstart.repository.*;
import hello.springstart.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

// Bean 등록 방법 2 - 자바 코드로 직접 스프링 빈 등록하는 방식
// -> 컴포넌트 스캔 방식에 비해 쳐야할 코드는 많지만,
// -> 변경 사항이 있을 경우, 해당 설정파일에서만 바꿔주면 되기 때문에 상대적으로 유지보수가 쉽다.
@Configuration
public class SpringConfig {

    // DB 연결을 획득할 때 사용하는 객체 > 연결 정보를 바탕으로 해당 객체 생성 후 스프링 빈으로 만들어 둠
//    private final DataSource dataSource;
//    // DI
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // JPA
//    @PersistenceContext
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // Spring Data JPA
    // 스프링 컨테이너에서 MemberRepository를 찾게 되면,
    // Spring Data JPA가 JpaRepository를 상속받고 있는 SpringDataJpaMemberRepository를 보고 해당 레포지토리에서 구현체를 자동으로 생성하고 빈으로도 만들어줌
    // 그래서 의존성 주입이 가능한 것 !
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


    // AOP 적용 시, 빈 등록 필수
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

    // SOLID 설계원칙 중, OCP 개방 폐쇄 원칙에 해당 : 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스 변
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

    // Bean 등록 2가지 방법에 대한 쓰임 정리 !!!
    // 실무에서는 주로 정형화 된 Controller / Service / Repository와 같은 코드는 '컴포넌트 스캔 방식' 사용
    // 정형화 되지 않고, 상황에 따라 구현 클래스를 변경해야하면 '자바 코드로 직접 스프링 빈에 등록 방식' 사용
}
