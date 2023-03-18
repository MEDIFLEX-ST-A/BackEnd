package com.mediflix.backend.controller;

import com.mediflix.backend.dto.ReqMemberDTO;
import com.mediflix.backend.dto.RespMemberDTO;
import com.mediflix.backend.repository.MemberList;
import com.mediflix.backend.service.MemberService;
import com.mediflix.backend.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    //로그인 결과를 받아오는 API
    @PostMapping("/login")
    public RespMemberDTO login(@RequestBody ReqMemberDTO reqMemberDTO, HttpSession session) {
        log.info("로그인 시작");
        RespMemberDTO loginResult = memberService.login(reqMemberDTO);
        String id = loginResult.getUserId();

        if(loginResult != null) {
            // 로그인 성공
            // 세션에다가 Email 정보를 담아준다.
            log.info("로그인 성공");
            SessionUtil.setLoginMemberId(session, id);

        }
        else {
            log.info("로그인 실패");
            // 로그인 실패
        }
        return loginResult;
    }

    //로그아웃
    @GetMapping("/admin/logout")
    public void logout(HttpSession session) {
        SessionUtil.clear(session);
    }

    //회원 목록 출력
    @GetMapping("/admin/list")
    public List adminList(Model model) {
        List<MemberList> memberLists = memberService.findAdminList();
        return memberLists;
    }

    //개인의 회원정보 상세조회
    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        RespMemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }
}

