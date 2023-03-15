package com.mediflix.backend.entity;

import com.mediflix.backend.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
// 이런 Table 이름이 있다면 지우고, 새로 생성하겠다!
@Table(name = "member")
public class Member {
    //요런 형식을 갖는 컬럼들이 생성되는 것이다.

    //회원 번호
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 지원
    private Long memberId;

    //아이디
    @Column(unique = true) // unique 제약 조건 추가
    private String userId;

    //비밀번호
    @Column(nullable = false)
    private String userPw;

    //닉네임
    @Column(nullable = false)
    private String nickname;

    //이름
    @Column(nullable = false)
    private String memberName;

    //역할
    @Column(nullable = false)
    private String role;

    //전공과
    @Column
    private String member_major;

    //접속 위치
    @Column
    private String place;

    //시청 시간
    @Column
    private Long watch_time;

    // 이후 DB에 값을 저장하는 메소드 실행. (DTO -> entity)
    public static Member toMemberEntity(MemberDTO memberDTO) {
        Member member = new Member();
        member.setUserId(memberDTO.getUserId()); // 매개변수는 DTO에 담긴 걸 entity에 넘기는 작업이므로 get!
        member.setUserPw(memberDTO.getUserPw());
        member.setMemberName(memberDTO.getMemberName());
        return member;
    }
}
