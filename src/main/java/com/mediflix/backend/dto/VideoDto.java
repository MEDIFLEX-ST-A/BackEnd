package com.mediflix.backend.dto;

import com.mediflix.backend.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoDto {
    String category;
    String title;
    String cast;
    Long view;

    public static VideoDto makeVideoDto(Video video){

        VideoDto videoDto = new VideoDto();
        videoDto.setCategory(video.getCategory());
        videoDto.setTitle(video.getTitle());
        videoDto.setCast(video.getCast());
        videoDto.setView(video.getView());

        return videoDto;
    }
}
