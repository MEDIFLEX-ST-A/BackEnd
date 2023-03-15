package com.mediflix.backend.service;

import com.mediflix.backend.dto.MemberDTO;
import com.mediflix.backend.entity.MemberEntity;
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

    // 회원가입 처리
    public void save(MemberDTO memberDTO) {
        // repositry의 save메서드를 호출해야 한다. (조건 : entity 객체를 넘겨줘야 한다.)
        // 1. dto -> entity 변환.
        // 2. repository의 save 메서드를 호출해야 한다.
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity); // save()메서드는 JPA가 제공하는 메서드임.

    }

    // 로그인 처리
    public MemberDTO login(MemberDTO memberDTO) {
        /*
        1. 회원이 입력한 이메일로 DB에서 조회를 한다.
        2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단한다.
         */
        Optional<MemberEntity> byUserId = memberRepository.findByUserId(memberDTO.getUserId());
        if (byUserId.isPresent()) {
            // 조회 결과가 있다.(해당 이메일을 가진 회원 정보가 있다.)
            MemberEntity memberEntity = byUserId.get();
            //test

            // 비밀번호가 같은지 확인..
            if (memberEntity.getUserPw().equals(memberDTO.getUserPw())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDTO dto = memberDTO.toMemberDTO(memberEntity);
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
    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            //DTO객체를 담기 위한 List
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));

            // 위의 문장이랑 같은 의미.
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    //개인 회원 상세조회 로직
    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        // 값이 존재할 때..
        if (optionalMemberEntity.isPresent()) {
            // Optional로 감싸진 값을 꺼내고, DTO에 리턴한다.

            //이 세줄을 맨 아래 한 줄로 표현 가능하다.
//            MemberEntity memberEntity = optionalMemberEntity.get();
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            return memberDTO;
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }
        else {
            return null;
        }
    }
}