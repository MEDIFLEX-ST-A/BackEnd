package com.mediflix.backend.entity;

import com.mediflix.backend.config.BaseTime;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
// 이런 Table 이름이 있다면 지우고, 새로 생성하겠다!
@Table(name = "member")
public class Member extends BaseTime {
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
    private int roleNum;

    //역할 이름(대리, 주임 ,,,)
    @Column
    private String role_name;

    //전공과
    @Column
    private String member_major;

    //접속 위치
    @Column
    private String place;

    //시청 시간
    @Column
    private Long watch_time;
}
