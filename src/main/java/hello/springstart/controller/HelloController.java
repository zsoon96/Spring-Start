package hello.springstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 웹어플리케이션에서 가장 첫번째의 진입점
@Controller
public class HelloController {

    // 실행 시 가장 먼저
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "soon!!");
        // view resolver가 해당 이름으로 된 html 파일을 찾아서 값 전달 ( == index.html )
        return "hello";
    }
}
