package com.mediflix.backend.repository;

import com.mediflix.backend.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// DATABASE 작업을 해주는 최종 인터페이스 라고 생각해주면 된다!
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //첫번째 인자는 어떤 Entity 클래스를 받을 것인지를 나타내고, 두 번째 인자는 Entity 클래스의 PK 타입을 적어주는 것이다.(ID)

    //이메일로 회원 정보를 조회함. (select * from member_table where member_email=?) 와 같은 형태
    // Optional 은 null 방지를 해줌.
    Optional<MemberEntity> findByUserId(String userId);

}
