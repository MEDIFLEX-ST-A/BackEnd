package com.mediflix.backend.dto;

import com.mediflix.backend.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RespMemberDTO {
    private Long memberId;
    private String userId;
    private String userPw;
    private String memberName;

    private String nickname;

    private int role_num;
    private String role_name;

    //해당 정보를 Controller에 제공.
    // entity -> dto 변환 후 리턴
    public static RespMemberDTO toMemberDTO (Member member) {
        RespMemberDTO respMemberDTO = new RespMemberDTO();
        respMemberDTO.memberId = member.getMemberId();
        respMemberDTO.userId = member.getUserId();
        respMemberDTO.userPw = member.getUserPw();
        respMemberDTO.memberName = member.getMemberName();
        respMemberDTO.nickname = member.getNickname();
        respMemberDTO.role_name = member.getRole_name();
        respMemberDTO.role_num = member.getRoleNum();
        return respMemberDTO;
    }
}
