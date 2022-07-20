package hello.springstart.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP를 사용하면 공통 관심 기능과 핵심 기능을 분리하고, 공통 관심 기능 사용을 원하는 곳을 지정해서 적용
    // 이로써, 핵심 관심 사항(핵심 로직)을 깔끔하게 유지할 수 있고, 변경이 필요하면 이 로직만 변경하면 됨 = 유지보수 굿!

// 스프링에서 AOP의 동작 방식
    // 스프링 컨테이너가 MemberController에서 MemberService를 호출할 때, MemberService가 aop 적용되어야 한다는걸 파악하면
    // 가짜(프록시) MemberService를 만들어 내고 (DI의 장점 = 알아서 필요 객체 생성 후 주입..) > aop 로직 실행후 > joinPoint.proceed() > 실제 MemberService 호출
        // 프록시를 사용하는 이유 : MemberController에서 MemberService를 직접적으로 호출하는 코드를 변경하지 않고도 원하는 작업을 끼워 넣을 수 있기 때문!!

// AOP 사용시 필요한 어노테이션
@Aspect
public class TimeTraceAop {

    // 해당 AOP 적용 대상 설정하는 어노테이션
    // == 해당 패키지의 하위 패키지에 모두 적용하되, SpringConfig는 대상에서 제외하겠다.
        // hello.springstart 패키지 안에 SpringConfig도 aop 대상이 되기에 (SpringConfig에서 aop 객체를 빈으로 설정해준 메서드도 aop로 처리됨)
        // 즉, 자기 자신도 aop 적용 대상이 되기 때문에 순환참조가 발생 !!!
        // 이를 방지하기 위하여 SpringConfig를 aop 대상에서 제외시켜주거나, 컴포넌트 스캔 방식으로 빈 등록 해줄 것!
    @Around("execution(* hello.springstart..*(..)) && !target(hello.springstart.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        // joinPoint.toString() : 어떤 메소드를 실행했는지 출력하기 위해
        System.out.println("START: " + joinPoint.toString());

        try {
            // 다음 메소드로 진행시켜주는 기능
            return joinPoint.proceed(); // 인라인
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
