package com.mediflix.backend.controller;

import com.mediflix.backend.dto.ReqGetDataDto;
import com.mediflix.backend.dto.ReqMemberDTO;
import com.mediflix.backend.dto.RespGetDataDto;
import com.mediflix.backend.dto.RespMemberDTO;
import com.mediflix.backend.repository.CountContents;
import com.mediflix.backend.repository.MajorList;
import com.mediflix.backend.repository.MemberList;
import com.mediflix.backend.response.CommonResponse;
import com.mediflix.backend.response.ErrorCode;
import com.mediflix.backend.service.MemberService;
import com.mediflix.backend.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unchecked")
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    //로그인 결과를 받아오는 API
    @PostMapping("/login")
    public CommonResponse<?> login(@RequestBody ReqMemberDTO reqMemberDTO, HttpSession session) {
        Optional<RespMemberDTO> loginResult = Optional.ofNullable(memberService.login(reqMemberDTO));

        if (loginResult.isPresent()) // 값이 존재한다면...
        {
            //로그인 성공, 세션 생성
            String id = loginResult.get().getUserId();
            SessionUtil.setLoginMemberId(session, id);
            return new CommonResponse<>(loginResult);
        }
        else {
            // 로그인 실패
            return new CommonResponse<>(ErrorCode.NOT_FOUND, Optional.empty());
        }
    }

    @GetMapping("/logs")
    public CommonResponse<?> getLog(ReqGetDataDto reqGetDataDto) {
        RespGetDataDto respGetDataDto = memberService.getData(reqGetDataDto);
        return new CommonResponse<>(respGetDataDto);
    }

    //로그아웃
    @GetMapping("/logout")
    public void logout(HttpSession session) {
        SessionUtil.clear(session);
    }

    //관리자 목록 출력
    @GetMapping("/admin/list")
    public List adminList(Model model) {
        List<MemberList> memberLists = memberService.findAdminList();
        return memberLists;
    }

    //전공과별 비율
    @GetMapping("/admin/major_ratio")
    public List majorList() {
        List<MajorList> majorLists = memberService.majorList();
        return majorLists;
    }

    // 전공별 콘텐츠 개수
    @GetMapping("/admin/major_content")
    public List contentMajor() {
        List<CountContents> countContents = memberService.CountMajor();
        return countContents;
    }
}

