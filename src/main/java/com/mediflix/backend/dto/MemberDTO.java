package com.mediflix.backend.dto;

import com.mediflix.backend.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    //회원 정보 필드를 저장함.
    private Long memberId;
    private String userId;
    private String userPw;
    private String memberName;

    private String member_major;

    // 로그인 과정에서 Service에서 entity -> DTO를 위한 함수
    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setUserId(memberEntity.getUserId());
        memberDTO.setUserPw(memberEntity.getUserPw());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMember_major(memberEntity.getMember_major());
        return memberDTO;
    }
}