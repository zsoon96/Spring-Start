package hello.springstart.controller;

import hello.springstart.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // @Autowired : MemberService를 스프링이 스프링 컨테이너에 있는 MemberService를 가져다 연결해주는 역할
    // 그러려면 MemberService가 Bean으로 등록되어 있어야 함
    // Bean 등록 방법 1 - 컴포넌트 스캔 방식으로 등록 =  @Service / @Repository / @Controller / @Component..
        // 스프링에서 Bean을 등록할 때, 싱글톤으로 등록해서 동일한 인스턴스로 공유함 !
        // -> 고정된 메모리 영역을 갖기 때문에 불필요한 메모리 누수를 방지
        // -> 공통된 오브젝트를 사용해야하는 상황에서 특정한 하나의 오브젝트만 사용할 수 있다는 이점을 가지고 있음
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
