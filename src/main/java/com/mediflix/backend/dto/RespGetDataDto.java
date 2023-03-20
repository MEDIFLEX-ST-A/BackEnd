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
public class RespGetDataDto {

    Integer todayVisit;
    Integer todaySignin;
    Integer visitMean;
    Integer todayWatch;
    Integer todayLeave;
    Integer totalSignin;
    Integer signinIncrease;
    Integer totalVideo;
    Integer videoIncrease;
    Integer watchMean;
    Integer partner;
    List<VideoDto> video;
    List<RespGetLogDto> logs;


    public static RespGetDataDto makerespGetLogDto(int todayVisit, int todaySignin, int visitMean, int todayWatch,
                                                   int todayLeave, int totalSignin, int signinIncrease, int totalVideo,
                                                   int videoIncrease, List<Video> videos, int watchMean, int partner, List<RespGetLogDto> logs) {

        List<VideoDto> video = new ArrayList<>();
        for(Video v : videos){
            VideoDto videoDto = VideoDto.makeVideoDto(v);

            video.add(videoDto);
        }

        RespGetDataDto respGetLogDto = RespGetDataDto.builder()
                .todayVisit(todayVisit)
                .todaySignin(todaySignin)
                .visitMean(visitMean)
                .todayWatch(todayWatch)
                .todayLeave(todayLeave)
                .totalSignin(totalSignin)
                .signinIncrease(signinIncrease)
                .totalVideo(totalVideo)
                .videoIncrease(videoIncrease)
                .watchMean(watchMean)
                .partner(partner)
                .video(video)
                .logs(logs)
                .build();

        return respGetLogDto;
    }
}
