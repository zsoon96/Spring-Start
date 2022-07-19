package hello.springstart.repository;

import hello.springstart.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Spring Data Jpa를 사용하게 되면 Repository에 구현 클래스 없이 인터페이스 만으로
// 기본적인 CRUD, 메서드 이름만으로 조회할 수 있는 기능(findByName(),findByEmail() 등), 페이지 기능을 자동 제공해주므로써 손쉽게 개발할 수 있기 때문에
// 개발 생산성을 보다 극대화할 수 있고, 코드도 더욱 간결해지는 마법이 일어남 !!
// 따라서 개발자는 핵심 비즈니스 로직을 개발하는데 집중할 수 있는 환경 제공

// 하지만, Spring Data JPA는 JPA를 편리하게 사용하도록 도와주는 기술이기 때문에
// JPA를 먼저 학습한 후 Spring Data JPA를 학습하는 걸 매우 권장함


// Spring Data Jpa가 JpaRepository를 상속 받고 있으면, 해당 레포지토리에서 자동으로 구현체를 만들어주고 빈을 생성하도록 해줌
// 그럼 우린 그걸 그대로 가져다 사용하면 됨 ! > SpringConfig 파일 참고

// 인터페이스가 인터페이스를 받게될 때 implements가 아닌 extends로 받기
// 인터페이스는 다중 상속가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 앞서 작성했던 기본 메서드가 JpaRepository에 정의가 되어 있기때문에 그냥 가져다 사용하면 됨^^
    // JPQL = select m from Member m where m.name=?
    // 서비스 요구사항에 따라 찾고자하는 속성을 대입해서 메서드 이름만으로 조회가 가능함 ! ex) 이메일로 찾고싶을경우 = findByEmail() / 아이디와 이름으로 찾고 싶을 경우 = findByNameAndId()
    @Override
    Optional<Member> findByName(String name);
}
