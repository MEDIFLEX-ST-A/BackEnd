package com.mediflix.backend.controller;

import com.mediflix.backend.dto.ReqMemberDTO;
import com.mediflix.backend.dto.RespMemberDTO;
import com.mediflix.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    //로그인 결과를 받아오는 API
    @PostMapping("/login")
    public RespMemberDTO login(@RequestBody final ReqMemberDTO params, HttpSession session) {
        RespMemberDTO loginResult = memberService.login(params);
        if(loginResult != null) {
            // 로그인 성공
            // 세션에다가 Email 정보를 담아준다.
            session.setAttribute("loginId", loginResult.getUserId());
            RespMemberDTO respMemberDTO = memberService.login(params);
            return respMemberDTO;
        }
        else {
            // 로그인 실패
            return null;
        }
    }

    //로그아웃
    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    //회원 목록 출력
    @GetMapping("/member/")
    public String findAll(Model model) {
        List<RespMemberDTO> respMemberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model을 사용한다.
        model.addAttribute("memberList", respMemberDTOList);
        return "list";
    }

    //개인의 회원정보 상세조회
    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        RespMemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }
}

