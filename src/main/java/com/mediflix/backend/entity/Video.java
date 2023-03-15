package com.mediflix.backend.entity;

import com.mediflix.backend.config.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "video")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Video extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long videoId;

    @Column(length = 20, nullable = false)
    private String category;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String cast;

    private Long view;

    private LocalDateTime uploadDate;

}
