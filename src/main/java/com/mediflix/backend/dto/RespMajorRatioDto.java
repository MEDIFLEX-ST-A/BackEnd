package com.mediflix.backend.dto;

import lombok.Data;

@Data
public class RespMajorRatioDto {
    private String member_major;
    private String major_ratio;
    private Long major_cnt;

    private Long total_cnt;

    public RespMajorRatioDto(String member_major, String major_ratio, Long major_cnt, Long total_cnt) {
        this.member_major = member_major;
        this.major_ratio = major_ratio;
        this.major_cnt = major_cnt;
        this.total_cnt = total_cnt;
    }
}
