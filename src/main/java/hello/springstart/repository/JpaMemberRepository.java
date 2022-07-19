package hello.springstart.repository;

import hello.springstart.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

// JPA를 사용하게되면 기존의 반복적인 코드를 제거해주고, 기본적인 SQL문도 직접 만들어서 실행해주기 때문에
// JPA를 사용하게되면 SQL 중심의 설계에서 객체 중심의 설계롤 패러다임을 전환할 수 있다.
// 뿐만 아니라 개발 생산성을 크게 높일 수 있다 !
public class JpaMemberRepository implements MemberRepository{

    // JPA는 엔티티 매니저를 통해 모든 임무를 수행 == JPA를 사용하려면 엔티티 매니저를 주입받아야 한다.
    // build.gradle에서 jpa 라이브러리를 추가하게 되면 스프링부트가 자동으로 엔티티 매니저를 생성해줌
    private final EntityManager em;
    // DI
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist = 영구 저장하는 환경 == 어플리케이션과 데이터베이스 사이에서 객체를 보관하는 가상의 환경
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // find (조회할 타입, pk)
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // pk 아닌 그 외 속성값으로 객체를 찾게되는 경우, JPQL이라는 객체지향쿼리를 사용해야함
    // 테이블이 아닌 객체를 대상으로 쿼리를 날려서 SQL문으로 번역이 됨
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> member = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        // 단일 데이터 조회시
        return member.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
