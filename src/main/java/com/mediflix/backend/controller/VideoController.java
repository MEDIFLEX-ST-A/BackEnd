package com.mediflix.backend.controller;

import com.mediflix.backend.dto.ReqPostViewDto;
import com.mediflix.backend.response.CommonResponse;
import com.mediflix.backend.response.ErrorCode;
import com.mediflix.backend.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/view")
    public CommonResponse<?> getVideo(ReqPostViewDto reqPostViewDto) {
        System.out.println(reqPostViewDto);
        System.out.println(reqPostViewDto.getNumber());
        videoService.updateView(reqPostViewDto);


        return new CommonResponse<>(ErrorCode.SUCCESS);
    }

}
