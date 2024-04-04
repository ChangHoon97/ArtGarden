package artgarden.server.config;

import artgarden.server.kopis.service.KopisServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataUpdateScheduler {

    private final KopisServiceImpl kopisService;

    @Scheduled(cron = "0 00 10 * * ?")
    public void dailyUpdate(){
        log.info("스케쥴링 자동 업데이트 시작 : " + LocalDateTime.now());
        kopisService.updateUpcoming( formatDate(LocalDate.now().plusMonths(1)));    //standard = 한달 이후
        kopisService.deletePerformed(LocalDate.now().minusMonths(1));   //standard = 한달 이전
        kopisService.updatePerformStatus();
        kopisService.updateOngoing();
        kopisService.updateRank("week", formatDate(LocalDate.now().minusDays(1)));
        kopisService.updateAreaCode();
        kopisService.updateGenreCode();
        log.info("스케쥴링 자동 업데이트 종료 : " + LocalDateTime.now());
    }

    //LocalDate -> String
    private String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return date.format(formatter);
    }
}
