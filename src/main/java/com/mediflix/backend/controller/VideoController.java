package com.mediflix.backend.controller;

import com.mediflix.backend.dto.*;
import com.mediflix.backend.response.CommonResponse;
import com.mediflix.backend.response.ErrorCode;
import com.mediflix.backend.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/view")
    public CommonResponse<?> getVideo(ReqPostViewDto reqPostViewDto) {
        videoService.updateView(reqPostViewDto);
        return new CommonResponse<>(ErrorCode.SUCCESS);
    }

    @GetMapping("/view/day")
    public CommonResponse<?> getVideoDay() {
        RespGetViewDto respGetViewDto = videoService.getDayView();
        return new CommonResponse<>(respGetViewDto);
    }

    @GetMapping("/view/week")
    public CommonResponse<?> getVideoWeek() {
        RespGetViewDto respGetViewDto = videoService.getWeekView();
        return new CommonResponse<>(respGetViewDto);
    }

    @GetMapping("/view/month")
    public CommonResponse<?> getVideoMonth() {
        RespGetViewDto respGetViewDto = videoService.getMonthView();
        return new CommonResponse<>(respGetViewDto);
    }

    @GetMapping("/view/all")
    public CommonResponse<?> getVideoAll() {
        RespGetViewDto respGetViewDto = videoService.getAllView();
        return new CommonResponse<>(respGetViewDto);
    }
}
