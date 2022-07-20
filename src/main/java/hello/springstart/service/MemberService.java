package hello.springstart.service;

import hello.springstart.domain.Member;
import hello.springstart.repository.MemberRepository;
import hello.springstart.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

// JPA를 사용하기 위해선 항상 Transactional 기능을 함께 사용해줘야한다.
// JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야한다 !!!
// 스프링은 해당 클래스의 메서드를 실행할 때, 트랜잭션을 시작하고 > 메서드가 정상 종료되면 트랜잭션을 커밋하게되는데, 만약 런타임 예외가 발생하면 롤백함!
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // service 단과 test 단에서의 동일한 repository를 사용해야하기 때문에
    // 생성자로 호출하여 외부에서 만든 repository를 주입해서 사용 가능하게 함 == 이게 바로 DI의 생성자 주입 방법!!!
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {

        // 시간 측정 = 공통 관심 사항
        long start = System.currentTimeMillis();

        // 핵심 로직 = 핵심 관심 사항
        try {
            // 같은 이름 중복 검증
            validateName(member);
            // 검증 후 저장
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join " + timeMs + "ms");
        }
        // 공통 핵심 사항과 관심 사항의 로직이 섞여 있을 경우, 유지보수가 어렵다.
        // 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어렵다.( 핵심 로직이 포함되어 있기 때문 )
        // 그러므로 이렇게 함께 사용했을 경우, 시간을 측정하는 로직을 변경할 때엔 모든 로직을 찾아가면서 변경해야한다....

    }

    // 로직이 나오는 경우에는 메서드로 추출해서 리팩터링 해주면 좋음
    private void validateName(Member member) {
        // Optional 타입으로 바로 반환하는 케이스 지양
        memberRepository.findByName(member.getName()) // 해당 구문 자체가 Optional 값이기 때문에 앞에 반환 타입(Optional) 생략 가능
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 회원 정보 모두 조회
    public List<Member> findAll() {

        // 시간 측정
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }

    }

    // 회원 정보 단일 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
