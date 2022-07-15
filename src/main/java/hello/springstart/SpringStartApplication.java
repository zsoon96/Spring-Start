package hello.springstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// springboot는 내장형 톰캣(WAS)을 사용하기 때문에 별도의 톰캣 설치나 버전 관리를 해주지 않아도 되서 편리함
@SpringBootApplication
public class SpringStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStartApplication.class, args);
    }

}
