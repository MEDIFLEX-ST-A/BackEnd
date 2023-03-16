package com.mediflix.backend.dto;

import com.mediflix.backend.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReqMemberDTO {
    private String userId;
    private String userPw;
}
