package com.mediflix.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RespGetLogDto {

    String logTime;
    Integer visit;
    Integer login;
    Integer watch;

    public static RespGetLogDto makeRespGetLogDto(String logTime, int visit, int login, int watch) {
        RespGetLogDto respGetLogDto = RespGetLogDto.builder()
                .logTime(logTime)
                .visit(visit)
                .login(login)
                .watch(watch)
                .build();

        return respGetLogDto;
    }
}
