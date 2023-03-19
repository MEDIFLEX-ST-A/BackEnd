package com.mediflix.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static RespGetDataDto makerespGetLogDto(int todayVisit, int todaySignin, int visitMean, int todayWatch, int todayLeave, int totalSignin, int signinIncrease, int totalVideo, int videoIncrease) {

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
                .build();

        return respGetLogDto;
    }
}
