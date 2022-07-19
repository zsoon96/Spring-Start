package hello.springstart.domain;

import javax.persistence.*;

// 데이터베이스와의 맵핑을 하는 자바 객체(엔티티)임을 설정
// == JPA가 관리하는 엔티티
@Entity
public class Member {
    @Id // pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 값을 넣으면 DB에서 Id를 자동으로 생성해주도록 설정
    private Long id;
    // @Column(name = "username") // 컬럼명을 별도로 지정해줄 경우
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
