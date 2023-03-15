package com.mediflix.backend.controller;

import com.mediflix.backend.dto.MemberDTO;
import com.mediflix.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    // DB에 정보 저장
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.exe");
        System.out.println("memberDTO = "+ memberDTO);
        memberService.save(memberDTO); //Service 객체로 DTO 객체를 넘겼다.
        return "login";
    }


    // 로그인 시작!!
    //로그인 띄워주는 페이지
    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    //로그인 결과를 받아오는 API
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null) {
            // 로그인 성공
            // 세션에다가 Email 정보를 담아준다.
            session.setAttribute("loginId", loginResult.getUserId());
            return "main";
        }
        else {
            // 로그인 실패
            return "login";
        }
    }

    //로그아웃
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    //회원 목록 출력
    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model을 사용한다.
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    //개인의 회원정보 상세조회
    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }
}

