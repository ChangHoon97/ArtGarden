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
        kopisService.updatePerformance(UtilBean.formatDate(LocalDate.now()), UtilBean.formatDate(LocalDate.now().plusMonths(1)), "01"); //한달 이후까지 공연 예정인 공연 업데이트
        kopisService.updateEndPerformStatus();
        kopisService.updatePerformance(UtilBean.formatDate(LocalDate.now()), UtilBean.formatDate(LocalDate.now()), "02"); // 오늘 공연중인 공연 업데이트
        kopisService.updateRank("week", UtilBean.formatDate(LocalDate.now().minusDays(1)));
        kopisService.updateAreaCode();
        kopisService.updateGenreCode();
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
