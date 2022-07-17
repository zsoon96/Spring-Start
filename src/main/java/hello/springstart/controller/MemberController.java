package hello.springstart.controller;

import hello.springstart.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Bean 등록 방법 1 - 컴포넌트 스캔 방식으로 등록 =  @Service / @Repository / @Controller / @Component..
// 스프링에서 Bean을 등록할 때, 싱글톤으로 등록해서 동일한 인스턴스로 공유함 !
// -> 고정된 메모리 영역을 갖기 때문에 불필요한 메모리 누수를 방지
// -> 공통된 오브젝트를 사용해야하는 상황에서 특정한 하나의 오브젝트만 사용할 수 있다는 이점을 가지고 있음
@Controller
public class MemberController {

    private final MemberService memberService;

    // @Autowired : MemberService를 스프링이 스프링 컨테이너에 있는 MemberService를 가져다 연결해주는 역할
    // 그러려면 MemberService가 Bean으로 등록되어 있어야 함
    // DI 주입 방법 1 - 생성자 주입 : 생성자 호출시점에서 딱 1번만 호출되는 것을 보장하기 때문에 객체를 불변하게 설계할 수 있어서 해당 방법을 권장
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // DI 주입 방법 2 - 필드 주입 : 외부에서 변경이 불가능하고, DI 프레임워크 없이는 작동하기 어렵기 때문에 해당 방법을 비권장
    // @Autowired private MemberService memberService2;

    // DI 주입 방법 3 - 수정자 주입 : 선택, 변경 가능성이 있는 의존관계에서 사용
    // @Autowired
    // public void setMemberService(MemberService memberService) {
    //     this.memberService = memberService;
    // }
}
