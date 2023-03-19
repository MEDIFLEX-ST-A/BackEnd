package com.mediflix.backend.repository;

import com.mediflix.backend.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    @Modifying
    @Query(value = "UPDATE Video v SET v.view = v.view + 1 WHERE v.videoId = :id")
    void updateView(Long id);
    @Query(value = "SELECT v FROM Video v WHERE v.updatedAt >= :start AND v.updatedAt < :end ORDER BY v.view DESC")
    List<Video> getVideo(LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT v FROM Video v WHERE v.updatedAt < :end ORDER BY v.view DESC")
    List<Video> getVideoAll(LocalDateTime end);
}
