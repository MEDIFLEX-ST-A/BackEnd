package com.mediflix.backend.repository;

import com.mediflix.backend.entity.Member;
import com.mediflix.backend.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// DATABASE 작업을 해주는 최종 인터페이스 라고 생각해주면 된다!
public interface MemberRepository extends JpaRepository<Member, Long> {
    //첫번째 인자는 어떤 Entity 클래스를 받을 것인지를 나타내고, 두 번째 인자는 Entity 클래스의 PK 타입을 적어주는 것이다.(ID)

    //이메일로 회원 정보를 조회함. (select * from member_table where member_email=?) 와 같은 형태
    // Optional 은 null 방지를 해줌.
    Optional<Member> findByUserId(String userId);
    // 관리자 목록 조회 SQL
    @Query(value = "SELECT m.memberName as member_name, m.role_name as role_name FROM Member m WHERE m.roleNum=1")
    List<MemberList> getAdminList(@Param("role_num") int role_num);

    @Query(value = "SELECT m.member_major as member_major from Member m WHERE m.roleNum=0")
    List<MajorList> getMajorList(@Param("role_num") int role_num);

    @Query(value = "SELECT count(m.member_major) from Member m WHERE m.member_major = :member_major")
    Long getMemberMajor(String member_major);

    //콘텐츠의 전공과 별 개수 구하는 쿼리문
    @Query(value = "SELECT count(v.major) from Video v WHERE v.major = :major")
    Long getCountMajorContent(String major);
}
