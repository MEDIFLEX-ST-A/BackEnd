package com.mediflix.backend.repository;

import com.mediflix.backend.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VideoRepository extends JpaRepository<Video, Long> {
    @Modifying
    @Query("UPDATE Video v SET v.view = v.view + 1,  v.updatedAt = now() WHERE v.videoId = :id")
    void updateView(Long id);
}
