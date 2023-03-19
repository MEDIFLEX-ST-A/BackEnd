package com.mediflix.backend.service;

import com.mediflix.backend.dto.ReqPostViewDto;
import com.mediflix.backend.dto.RespGetViewDto;
import com.mediflix.backend.entity.Video;
import com.mediflix.backend.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class VideoService {
    private final VideoRepository videoRepository;

    public void updateView (ReqPostViewDto reqPostViewDto) {
        videoRepository.updateView(reqPostViewDto.getNumber());
    }

    public RespGetViewDto getDayView() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);

        List<Video> videos = videoRepository.getVideo(start, end);

        RespGetViewDto respGetViewDto = RespGetViewDto.makeRespGetViewDto(videos);
        return respGetViewDto;
    }


    public RespGetViewDto getWeekView() {
        LocalDateTime start = LocalDate.now().atStartOfDay().minusDays(6);
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);;

        List<Video> videos = videoRepository.getVideo(start, end);

        RespGetViewDto respGetViewDto = RespGetViewDto.makeRespGetViewDto(videos);
        return respGetViewDto;
    }

    public RespGetViewDto getMonthView() {
        LocalDateTime start = LocalDate.now().atStartOfDay().withDayOfMonth(1);
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);;

        List<Video> videos = videoRepository.getVideo(start, end);

        RespGetViewDto respGetViewDto = RespGetViewDto.makeRespGetViewDto(videos);
        return respGetViewDto;
    }

    public RespGetViewDto getAllView() {
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);;

        List<Video> videos = videoRepository.getVideoAll(end);

        RespGetViewDto respGetViewDto = RespGetViewDto.makeRespGetViewDto(videos);
        return respGetViewDto;
    }



}
