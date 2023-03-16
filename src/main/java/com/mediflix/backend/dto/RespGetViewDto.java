package com.mediflix.backend.dto;

import com.mediflix.backend.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RespGetViewDto {

    List<VideoDto> video;

    public static RespGetViewDto makeRespGetViewDto(List<Video> videos){
        List<VideoDto> video = new ArrayList<>();
        for(Video v : videos){
            VideoDto videoDto = VideoDto.makeVideoDto(v);

            video.add(videoDto);
        }
        RespGetViewDto respGetViewDto = RespGetViewDto.builder()
                .video(video)
                .build();

        return respGetViewDto;
    }
}
