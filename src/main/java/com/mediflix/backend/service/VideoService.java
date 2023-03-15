package com.mediflix.backend.service;

import com.mediflix.backend.dto.ReqPostViewDto;
import com.mediflix.backend.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@RequiredArgsConstructor
@Transactional
@Service
public class VideoService {
    private final VideoRepository videoRepository;

    public void updateView (ReqPostViewDto reqPostViewDto) {
        videoRepository.updateView(reqPostViewDto.getNumber());
    }
}
