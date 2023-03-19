package com.mediflix.backend.repository;

import com.mediflix.backend.entity.MemberLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberLogRepository extends JpaRepository<MemberLog, Long> {

    @Query(value = "SELECT m FROM MemberLog m WHERE m.updatedAt < :end AND m.serviceName LIKE :likeFormat")
    List<MemberLog> getLogs(LocalDateTime end, String likeFormat);

    @Query(value = "SELECT m FROM MemberLog m WHERE m.updatedAt >= :start AND m.updatedAt < :end AND m.serviceName LIKE :likeFormat")
    List<MemberLog> getTodayLogs(LocalDateTime start, LocalDateTime end, String likeFormat);
}
