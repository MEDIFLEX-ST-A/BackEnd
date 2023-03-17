package com.mediflix.backend.repository;

import com.mediflix.backend.entity.Member;
import com.mediflix.backend.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// DATABASE 작업을 해주는 최종 인터페이스 라고 생각해주면 된다!
public interface MemberRepository extends JpaRepository<Member, Long> {
    //첫번째 인자는 어떤 Entity 클래스를 받을 것인지를 나타내고, 두 번째 인자는 Entity 클래스의 PK 타입을 적어주는 것이다.(ID)

    //이메일로 회원 정보를 조회함. (select * from member_table where member_email=?) 와 같은 형태
    // Optional 은 null 방지를 해줌.
    Optional<Member> findByUserId(String userId);

    @Query(value = "SELECT m FROM Member m WHERE m.roleNum=1")
    List<Member> getAdminList();

}
