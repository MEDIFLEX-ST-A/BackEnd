package com.mediflix.backend.service;

import com.mediflix.backend.dto.ReqMemberDTO;
import com.mediflix.backend.dto.RespMemberDTO;
import com.mediflix.backend.entity.Member;
import com.mediflix.backend.repository.MemberList;
import com.mediflix.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 스프링 빈으로 등록
@Service
@RequiredArgsConstructor
public class MemberService {
    // 생성자 주입
    private final MemberRepository memberRepository;

    // 로그인 처리
    public RespMemberDTO login(ReqMemberDTO reqMemberDTO) {
        /*
        1. 회원이 입력한 이메일로 DB에서 조회를 한다.
        2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단한다.
         */
        Optional<Member> byUserId = memberRepository.findByUserId(reqMemberDTO.getUserId());
        if (byUserId.isPresent()) {
            // 조회 결과가 있다.(해당 이메일을 가진 회원 정보가 있다.)
            Member member = byUserId.get();
            // 비밀번호가 같은지 확인..
            if (member.getUserPw().equals(reqMemberDTO.getUserPw())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                RespMemberDTO dto = RespMemberDTO.toMemberDTO(member);
                return dto;
            }
            else {
                //비밀번호 불일치(로그인 실패)
                return null;
            }
        }
        else {
            // 조회 결과가 없다.(해당 이메일을 가진 회원이 없다.) => 로그인 실패
            return null;
        }
    }

    // 회원목록 조회 로직
    public List<MemberList> findAdminList() {
        List<MemberList> memberList = memberRepository.getAdminList(1);
        return memberList;
    }

    //개인 회원 상세조회 로직
    public RespMemberDTO findById(Long id) {
        Optional<Member> optionalMemberEntity = memberRepository.findById(id);
        // 값이 존재할 때..
        if (optionalMemberEntity.isPresent()) {
            // Optional로 감싸진 값을 꺼내고, DTO에 리턴한다.

            //이 세줄을 맨 아래 한 줄로 표현 가능하다.
//            MemberEntity memberEntity = optionalMemberEntity.get();
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            return memberDTO;
            return RespMemberDTO.toMemberDTO(optionalMemberEntity.get());
        }
        else {
            return null;
        }
    }
}