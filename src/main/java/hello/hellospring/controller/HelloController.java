package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    // get메소드, post메소드 할때의 역할
    public String  hello(Model model){
        model.addAttribute("data", "spring!!!");
        return "hello";     // 컨트롤러 리턴 값으로 문자값 반환 => viewResolver가 화면 찾아 templates 목록에서 반환
    }

    @GetMapping("hello-mvc")
    public  String helloMvc(@RequestParam("name1") String name, Model model){
        model.addAttribute("name1", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;     // "hello spring"   => veiw가 없이 바로 html - body에 찍어냄
                                    // viewResolver 대신에 HttpMessageConverter가 동작
    }

    @GetMapping("hello-api")    // json 방식으로 나옴
    @ResponseBody
    public  Hello helloApi(@RequestParam("name1") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;       // 문자면 StringConverter, 객체면 JsonConverter로 통신
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;

        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
