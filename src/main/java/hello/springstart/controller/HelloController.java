package hello.springstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 웹어플리케이션에서 가장 첫번째의 진입점
@Controller
public class HelloController {

    // 실행 시 가장 먼저
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "적용 성공!!");
        // view resolver가 해당 이름으로 된 html 파일을 찾아서 값 전달 ( == index.html )
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-templates";
    }

    // API - String 값으로 데이터 보내기
    @GetMapping("hello-string")
    // http body 부분에 해당 데이터를 직접 넣어주기 위한 기능
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        // @ResponseBody로 바로 넘기게 되면 view resolver 대신 'HttpMessageConverter'가 동작하면서
        // 담긴 데이터 형태가 객체이면 JsonConverter가 동작하고, 문자이면 StringConverter가 동작하면서 Java 객체를 설정한 데이터 형태로 변환하여 전달
            // 기본 문자처리 - StringHttpMessageConverter가 동작
        return "hello " + name;
    }

    // API - 객체 (JSON) 값으로 데이터 보내기 = 실무에서 자주 사용하는 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // 기본 객체처리 - MappingJackson2HttpMessageConverter
            // 객체 > JSON 으로 변환해주는 대표 라이브러리 - Jackson / Gson = 스프링은 Jackson을 기본으로 탑재
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
