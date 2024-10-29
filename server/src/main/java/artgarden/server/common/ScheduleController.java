package artgarden.server.common;

import artgarden.server.common.util.UtilBean;
import artgarden.server.culture.service.CultureService;
import artgarden.server.kopis.service.KopisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleController {

    private final KopisService kopisService;
    private final CultureService cultureService;


    @Scheduled(cron = "0 30 00 * * ?")
    public void KopisDailyUpdate(){
        log.info("KOPIS 스케쥴링 자동 업데이트 시작 : " + LocalDateTime.now());
        kopisService.updateUpcoming( UtilBean.formatDate(LocalDate.now().plusMonths(1)));    //standard = 한달 이후
        //kopisService.deletePerformed(LocalDate.now().minusMonths(1));   //standard = 한달 이전
        kopisService.updatePerformStatus();
        log.info("공연 완료처리 끝");
        kopisService.updateOngoing();
        log.info("오늘 공연중인 공연 저장 처리 끝");
        kopisService.updateRank("week", UtilBean.formatDate(LocalDate.now().minusDays(1)));
        log.info("랭크 처리 끝");
        kopisService.updateAreaCode();
        log.info("지역코드 처리 끝");
        kopisService.updateGenreCode();
        log.info("장르코드 처리 끝");
        log.info("KOPIS 스케쥴링 자동 업데이트 종료 : " + LocalDateTime.now());
    }

    @Scheduled(cron = "0 00 00 * * ?")
    public void CultureDailyUpdate() throws Exception {
        log.info("CULTURE 스케쥴링 자동 업데이트 시작 : " + LocalDateTime.now());
        cultureService.updateExhibitList("D000");
        log.info("CULTURE 스케쥴링 자동 업데이트 종료 : " + LocalDateTime.now());
    }

    @Scheduled(cron = "0 15 00 * * ?")
    public void CultureStatusUpdate() throws Exception {
        log.info("CULTURESTATUS 스케쥴링 자동 업데이트 시작 : " + LocalDateTime.now());
        cultureService.updateStatus();
        log.info("CULTURESTATUS 스케쥴링 자동 업데이트 종료 : " + LocalDateTime.now());
    }

}
