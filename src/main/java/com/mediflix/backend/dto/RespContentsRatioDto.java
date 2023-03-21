package com.mediflix.backend.dto;

import lombok.Data;

@Data
public class RespContentsRatioDto {
    private String major; // 전공과 이름
    private Long major_cnt; // 전공 개수
    private String major_ratio; // 전공 비율
    private Long total_cnt; // 총 개수

    public RespContentsRatioDto(String major, Long major_cnt, String major_ratio, Long total_cnt) {
        this.major = major;
        this.major_cnt = major_cnt;
        this.major_ratio = major_ratio;
        this.total_cnt = total_cnt;
    }
}
