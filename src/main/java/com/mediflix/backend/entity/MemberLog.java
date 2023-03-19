package com.mediflix.backend.entity;

import com.mediflix.backend.config.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberLog extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_log_id")
    private Long AuthLogId;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "service_name", nullable = false, length = 30)
    private String serviceName;

}
