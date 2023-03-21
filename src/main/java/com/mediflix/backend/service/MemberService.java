package com.mediflix.backend.service;

import com.mediflix.backend.dto.*;
import com.mediflix.backend.entity.Member;
import com.mediflix.backend.entity.MemberLog;
import com.mediflix.backend.entity.Partner;
import com.mediflix.backend.entity.Video;
import com.mediflix.backend.repository.*;
import lombok.RequiredArgsConstructor;
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
    private final PartnerRepository partnerRepository;

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
        LocalDateTime prevstart = day.atStartOfDay().minusDays(10);
        List<Video> videos = videoRepository.getVideo(start, end);

        List<RespGetLogDto> logs = getPreviousLogs(prevstart);

        RespGetDataDto respGetDataDto = getLogs(start, end, prevend, videos, logs);

        return respGetDataDto;
    }

    // 회원목록 조회 로직
    public List<MemberList> findAdminList() {
        List<MemberList> memberList = memberRepository.getAdminList(1);
        return memberList;
    }

    // 전광과별 비율
    public List majorList() {
        List<MajorList> majorLists = memberRepository.getMajorList(0);
        // 전공과 총 개수
        Long cnt = Long.valueOf(majorLists.size());
        Long nerve = memberRepository.getMemberMajor("신경과");
        Long bone = memberRepository.getMemberMajor("정형외과");
        Long home = memberRepository.getMemberMajor("가정의학과");
        Long digest = memberRepository.getMemberMajor("소화기내과");
        Long inner = memberRepository.getMemberMajor("내분비내과");
        Long rmtis = memberRepository.getMemberMajor("류마티스내과");

        String nerve_ratio = String.format("%.0f", (double) nerve / (double) cnt * 100) + "%"; // 신경과 비율
        String bone_ratio = String.format("%.0f", (double) bone / (double) cnt * 100) + "%"; // 정형외과 비율
        String home_ratio = String.format("%.0f", (double) home / (double) cnt * 100) + "%"; // 가정의학과 비율
        String digest_ratio = String.format("%.0f", (double) digest / (double) cnt * 100) + "%"; // 소화기내과 비율
        String inner_ratio = String.format("%.0f", (double) inner / (double) cnt * 100) + "%"; // 내분비내과 비율
        String rmtis_ratio = String.format("%.0f", (double) rmtis / (double) cnt * 100) + "%"; // 류마티스내과 비율

        List<RespMajorRatioDto> respMajorRatioDtos = new ArrayList<>();
        respMajorRatioDtos.add(new RespMajorRatioDto("신경과", nerve_ratio, nerve, cnt));
        respMajorRatioDtos.add(new RespMajorRatioDto("정형외과", bone_ratio, bone, cnt));
        respMajorRatioDtos.add(new RespMajorRatioDto("가정의학과", home_ratio, home, cnt));
        respMajorRatioDtos.add(new RespMajorRatioDto("소화기내과", digest_ratio, digest, cnt));
        respMajorRatioDtos.add(new RespMajorRatioDto("내분비내과", inner_ratio, inner, cnt));
        respMajorRatioDtos.add(new RespMajorRatioDto("류마티스내과", rmtis_ratio, rmtis, cnt));

        return respMajorRatioDtos;
    }

    // 전공 별 콘텐츠 개수 및 비율
    public List CountMajor() {
        List<Video> videoList = videoRepository.findAll();
        // 모든 콘텐츠 개수
        Long cnt_content = Long.valueOf(videoList.size());
        Long content_nerve = memberRepository.getCountMajorContent("신경과");
        Long content_bone = memberRepository.getCountMajorContent("정형외과");
        Long content_digest = memberRepository.getCountMajorContent("소화기내과");
        Long content_inner = memberRepository.getCountMajorContent("내분비내과");
        Long content_rmtis = memberRepository.getCountMajorContent("류마티스내과");
        Long content_home = memberRepository.getCountMajorContent("가정의학과");
        //나머지
        Long remain = cnt_content - (content_nerve + content_bone + content_digest + content_inner + content_rmtis + content_home);

        String content_nerve_ratio = String.format("%.0f", (double) content_nerve / (double) cnt_content * 100) + "%"; // 신경과 비율
        String content_bone_ratio = String.format("%.0f", (double) content_bone / (double) cnt_content * 100) + "%"; // 정형외과 비율
        String content_digest_ratio = String.format("%.0f", (double) content_digest / (double) cnt_content * 100) + "%"; // 소화기내과 비율
        String content_inner_ratio = String.format("%.0f", (double) content_inner / (double) cnt_content * 100) + "%"; // 내분비내과 비율
        String content_rmtis_ratio = String.format("%.0f", (double) content_rmtis / (double) cnt_content * 100) + "%"; // 류마티스내과 비율
        String content_home_ratio = String.format("%.0f", (double) content_home / (double) cnt_content * 100) + "%"; // 가정의학과 비율

        String remain_ratio = String.format("%.0f", (double) remain / (double) cnt_content * 100) + "%"; // 나머지 비율

        List<RespContentsRatioDto> respContentsRatioDtos = new ArrayList<>();
        respContentsRatioDtos.add(new RespContentsRatioDto("신경과", content_nerve, content_nerve_ratio, cnt_content));
        respContentsRatioDtos.add(new RespContentsRatioDto("정형외과", content_bone, content_bone_ratio, cnt_content));
        respContentsRatioDtos.add(new RespContentsRatioDto("소화기내과", content_digest, content_digest_ratio, cnt_content));
        respContentsRatioDtos.add(new RespContentsRatioDto("내분비내과", content_inner, content_inner_ratio, cnt_content));
        respContentsRatioDtos.add(new RespContentsRatioDto("류마티스내과", content_rmtis, content_rmtis_ratio, cnt_content));
        respContentsRatioDtos.add(new RespContentsRatioDto("가정의학과", content_home, content_home_ratio, cnt_content));
        respContentsRatioDtos.add(new RespContentsRatioDto("그 외", remain, remain_ratio, cnt_content));
        return respContentsRatioDtos;
    }

    //편의 메서드
    private RespGetDataDto getLogs(LocalDateTime start, LocalDateTime end, LocalDateTime prevend, List<Video> video, List<RespGetLogDto> logs) {
        String service1 = "login";
        String service2 = "signin";
        String service3 = "visit";
        String service4 = "watch";
        List<MemberLog> visitlog = memberLogRepository.getTodayLogs(start, end, service3);
        List<MemberLog> visitlogs = memberLogRepository.getLogs(end, service3);
        List<MemberLog> sigininlog = memberLogRepository.getTodayLogs(start, end, service2);
        List<MemberLog> signinlogs = memberLogRepository.getLogs(end, service2);
        List<MemberLog> watchlog = memberLogRepository.getTodayLogs(start, end, service4);
        List<MemberLog> watchlogs = memberLogRepository.getLogs(end, service4);
        List<MemberLog> loginlog = memberLogRepository.getTodayLogs(start, end, service1);
        List<Video> videos = videoRepository.getVideoAll(end);
        List<Partner> partners = partnerRepository.findAll();

        List<MemberLog> prevsigninlogs = memberLogRepository.getLogs(prevend, service2);
        List<Video> prevVideos = videoRepository.getVideoAll(prevend);

        int todayVisit = visitlog.size();
        int todaySignin = sigininlog.size();
        int visitMean = visitlogs.size() / 10;
        int todayWatch = watchlog.size();
        int todayLeave = visitlog.size() - loginlog.size();
        int signinIncrease = signinlogs.size() - prevsigninlogs.size();
        int videoIncrease = videos.size() - prevVideos.size();
        int watchMean = watchlogs.size() / 10;
        int partner = partners.size();

        RespGetDataDto respGetLogDto = RespGetDataDto.makerespGetLogDto(todayVisit, todaySignin, visitMean, todayWatch, todayLeave, signinlogs.size(), signinIncrease, videos.size(), videoIncrease, video, watchMean, partner, logs);

        return respGetLogDto;
    }

    private List<RespGetLogDto> getPreviousLogs(LocalDateTime start) {
        List<RespGetLogDto> logs = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            LocalDateTime end = start.toLocalDate().atTime(LocalTime.MAX);
            List<MemberLog> visitlog = memberLogRepository.getTodayLogs(start, end, "visit");
            List<MemberLog> loginlog = memberLogRepository.getTodayLogs(start, end, "login");
            List<MemberLog> watchlog = memberLogRepository.getTodayLogs(start, end, "watch");
            RespGetLogDto respGetLogDto = RespGetLogDto.makeRespGetLogDto(start.toString(), visitlog.size(), loginlog.size(), watchlog.size());
            logs.add(respGetLogDto);
            start = start.toLocalDate().atStartOfDay().plusDays(1);
        }
        return logs;
    }
}