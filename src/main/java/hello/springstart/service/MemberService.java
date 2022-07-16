package hello.springstart.service;

import hello.springstart.domain.Member;
import hello.springstart.repository.MemberRepository;
import hello.springstart.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    // service 단과 test 단에서의 동일한 repository를 사용해야하기 때문에
    // 생성자로 호출하여 외부에서 만든 repository를 주입해서 사용 가능하게 함 == 이게 바로 DI의 생성자 주입 방법!!!!
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        // 같은 이름 중복 검증
        validateName(member);
        // 검증 후 저장
        memberRepository.save(member);
        return member.getId();
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
        return memberRepository.findAll();
    }

    // 회원 정보 단일 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
