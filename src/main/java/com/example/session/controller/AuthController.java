package com.example.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    // 로그인 메인 화면
    @GetMapping("/main")
    public String main() {
        return "/auth/main";
    }

    /*
     * [접속 방식]
     *      [HTML]
     *          1. Controller를 통해 ((cors)-html-login.html 으로) GetMapping
     *          2. (cors)-html-login.html 출력
     *          3. <form>을 통해 POST 요청
     *          3. Controller를 통해 (main.html 으로) PostMapping
     *          4. main.html 출력
     *      [FETCH]
     *          1. Controller를 통해 ((cors)-fetch-login.html 으로) GetMapping
     *          2. (cors)-fetch-login.html 출력
     *          3. fetch()를 통해 POST 요청 (응답 대기)
     *          4. Controller를 통해 ((cors)-fetch-login.html 으로) 응답 (로그인 성공 여부)
     *          5. (cors)-fetch-login.html에서 응답 결과가 success인 경우, main.html으로 이동
     *          6. main.html 출력
     */

    // 1) 기본 HTML form 로그인 화면
    @GetMapping("/html/login")
    public String htmlLogin() {
        return "/auth/html-login";
    }

    // 1) 기본 form 로그인 POST
    @PostMapping("/html/login")
    public String htmlLoginPost(@RequestParam String username, @RequestParam String password) {
        // 로그인 성공 가정
        return "redirect:/auth/main";
    }

    // 2) fetch(javascript) 로그인 화면
    @GetMapping("/fetch/login")
    public String fetchLogin() {
        return "/auth/fetch-login";
    }

    // 2) fetch 로그인 POST
    @PostMapping("/fetch/login")
    @ResponseBody
    public String fetchLoginPost(@RequestParam String username, @RequestParam String password) {
        // 로그인 성공 가정
        return "success";
    }

    /*
        [CrossOrigin의 위험성]
            CSRF 공격과 마찬가지인 통신

        [예시]
            1. 웹사이트 로그인 중
            2. 다른 사이트에서 버튼 클릭 (버튼은 웹사이트의 api를 호출하는 버튼)
            3. 세션 및 쿠키가 유지되기 때문에, 호출이 성공적으로 수행됨

        [위험성을 감소시키는 방법]
            1. @CrossOrigin(origins="허용하고자 하는 URL, ...", allowCredential="true") 
                -> (origins & allowCredential) 옵션을 통해 허용하고자 하는 URL만 허용
    */

    /*
        서버가 다르면(==CrossOrigin) 쿠키 송수신 불가
            -> 서버에서 @CrossOrigin의 (origins & allowCredential)의 옵션을 허용하도록 설정
                & fetch()에 credential="include"를 추가하면 해결 가능
    */

    // [LiveServer (port:5500) = 스프링부트와는 다른 서버로 인식]
    // 3) 크로스 오리진 + form 로그인 POST
    @PostMapping("/cors/html/login")
    public String corsHtmlLoginPost(@RequestParam String username, @RequestParam String password) {
        return "redirect:/auth/main";
    }

    // [LiveServer (port:5500) = 스프링부트와는 다른 서버로 인식]
    // 4) 크로스 오리진 + fetch 로그인 POST
    @CrossOrigin
    @PostMapping("/cors/fetch/login")
    @ResponseBody
    public String corsFetchLoginPost(@RequestParam String username, @RequestParam String password, HttpSession session) {
        return "success";
    }

}
