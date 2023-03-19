package com.mediflix.backend.service;

import com.mediflix.backend.dto.ReqGetDataDto;
import com.mediflix.backend.dto.ReqMemberDTO;
import com.mediflix.backend.dto.RespGetDataDto;
import com.mediflix.backend.dto.RespMemberDTO;
import com.mediflix.backend.entity.Member;
import com.mediflix.backend.entity.MemberLog;
import com.mediflix.backend.entity.Video;
import com.mediflix.backend.repository.MemberList;
import com.mediflix.backend.repository.MemberLogRepository;
import com.mediflix.backend.repository.MemberRepository;
import com.mediflix.backend.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 스프링 빈으로 등록
@Service
@RequiredArgsConstructor
public class MemberService {
    // 생성자 주입
    private final MemberRepository memberRepository;
    private final MemberLogRepository memberLogRepository;
    private final VideoRepository videoRepository;

    // 로그인 처리
    public RespMemberDTO login(ReqMemberDTO reqMemberDTO) {
        /*
        1. 회원이 입력한 이메일로 DB에서 조회를 한다.
        2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단한다.
         */
        Optional<Member> byUserId = memberRepository.findByUserId(reqMemberDTO.getUserId());
        if (byUserId.isPresent()) {
            // 조회 결과가 있다.(해당 이메일을 가진 회원 정보가 있다.)
            Member member = byUserId.get();
            // 비밀번호가 같은지 확인..
            if (member.getUserPw().equals(reqMemberDTO.getUserPw())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                RespMemberDTO dto = RespMemberDTO.toMemberDTO(member);
                return dto;
            }
            else {
                //비밀번호 불일치(로그인 실패)
                return null;
            }
        }
        else {
            // 조회 결과가 없다.(해당 이메일을 가진 회원이 없다.) => 로그인 실패
            return null;
        }
    }

    //대시보드 데이터 호출
    public RespGetDataDto getData(ReqGetDataDto reqGetDataDto) {
        LocalDate day = reqGetDataDto.getSelectDate().toLocalDate();
        LocalDateTime start = day.atStartOfDay();
        LocalDateTime end = day.atTime(LocalTime.MAX);
        LocalDateTime prevend = day.atTime(LocalTime.MAX).minusDays(1);

        RespGetDataDto respGetDataDto = getLogs(start, end, prevend);

        return respGetDataDto;
    }

    // 회원목록 조회 로직
    public List<MemberList> findAdminList() {
        List<MemberList> memberList = memberRepository.getAdminList(1);
        return memberList;
    }

    //개인 회원 상세조회 로직
    public RespMemberDTO findById(Long id) {
        Optional<Member> optionalMemberEntity = memberRepository.findById(id);
        // 값이 존재할 때..
        if (optionalMemberEntity.isPresent()) {
            // Optional로 감싸진 값을 꺼내고, DTO에 리턴한다.

            //이 세줄을 맨 아래 한 줄로 표현 가능하다.
//            MemberEntity memberEntity = optionalMemberEntity.get();
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            return memberDTO;
            return RespMemberDTO.toMemberDTO(optionalMemberEntity.get());
        }
        else {
            return null;
        }
    }



    //편의 메서드
    private RespGetDataDto getLogs(LocalDateTime start, LocalDateTime end, LocalDateTime prevend) {
        String service1 = "login";
        String service2 = "signin";
        String service3 = "visit";
        String service4 = "watch";
        List<MemberLog> visitlog = memberLogRepository.getTodayLogs(start, end, service3);
        List<MemberLog> visitlogs = memberLogRepository.getLogs(end, service3);
        List<MemberLog> sigininlog = memberLogRepository.getTodayLogs(start, end, service2);
        List<MemberLog> signinlogs = memberLogRepository.getLogs(end, service2);
        List<MemberLog> watchlog = memberLogRepository.getTodayLogs(start, end, service4);
        List<MemberLog> loginlog = memberLogRepository.getTodayLogs(start, end, service1);
        List<Video> videos = videoRepository.getVideoAll(end);

        List<MemberLog> prevsigninlogs = memberLogRepository.getLogs(prevend, service2);
        List<Video> prevVideos = videoRepository.getVideoAll(prevend);

        int todayVisit = visitlog.size();
        int todaySignin = sigininlog.size();
        int visitMean = visitlogs.size() / 10;
        int todayWatch = watchlog.size();
        int todayLeave = visitlog.size() - loginlog.size();
        int signinIncrease = signinlogs.size() - prevsigninlogs.size();
        int videoIncrease = videos.size() - prevVideos.size();

        RespGetDataDto respGetLogDto = RespGetDataDto.makerespGetLogDto(todayVisit, todaySignin, visitMean, todayWatch, todayLeave, signinlogs.size(), signinIncrease, videos.size(), videoIncrease);

        return respGetLogDto;
    }
}