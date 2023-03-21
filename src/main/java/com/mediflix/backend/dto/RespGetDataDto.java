package com.mediflix.backend.dto;

import com.mediflix.backend.entity.Video;
import com.mediflix.backend.repository.CountContents;
import com.mediflix.backend.repository.MajorList;
import com.mediflix.backend.repository.MemberList;
import com.mediflix.backend.utils.SessionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;
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

    //Son
    //관리자 목록
    List<MemberList> memberList;
    //전공과 개수 및 비율
    List<RespMajorRatioDto> majorLists;

    //전공별 컨텐츠 개수 및 비율
    List<RespContentsRatioDto> countContents;

    public static RespGetDataDto makerespGetLogDto(int todayVisit, int todaySignin, int visitMean, int todayWatch,
                                                   int todayLeave, int totalSignin, int signinIncrease, int totalVideo,
                                                   int videoIncrease, List<Video> videos, int watchMean, int partner,
                                                   List<RespGetLogDto> logs, List<MemberList> memberList, List<RespMajorRatioDto> majorLists,
                                                   List<RespContentsRatioDto> countContents) {

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
                .memberList(memberList)
                .majorLists(majorLists)
                .countContents(countContents)
                .build();

        return respGetLogDto;
    }
}
