package hello.springstart.service;

import hello.springstart.domain.Member;
import hello.springstart.repository.MemberRepository;
import hello.springstart.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원가입
    public Long join(Member member) {
        // 같은 이름 중복 검증
        Optional<Member> result = memberRepository.findByName(member.getName());

        // 기존에 알고 있는 방식으로 검증 구현
        // if ( result.isPresent() ) {
        //     throw new IllegalStateException("이미 존재하는 회원입니다.");
        // }

        // 강의에서 사용하는 방식으로 검증 구현
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다,");
        });

        // 검증 후 저장
        memberRepository.save(member);
        return member.getId();
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
