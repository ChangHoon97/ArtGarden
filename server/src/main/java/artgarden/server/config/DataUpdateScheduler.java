package artgarden.server.config;

import artgarden.server.service.KopisService;
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

    private final KopisService kopisService;

    @Scheduled(cron = "0 0 10 * * ?")
    public void dailyUpdate(){
        kopisService.updateUpcoming( formatDate(LocalDate.now().plusMonths(1)));    //standard = 한달 이후
        kopisService.deletePerformed(LocalDate.now().minusMonths(1));   //standard = 한달 이전
        kopisService.updatePerformStatus();
        kopisService.updateOngoing();
        kopisService.updateRank("week", formatDate(LocalDate.now().minusDays(1)));
        kopisService.updateAreaCode();
        kopisService.updateGenreCode();
        log.info("스케쥴링 자동 업데이트 : " + LocalDateTime.now());
    }

    //LocalDate -> String
    private String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return date.format(formatter);
    }
}
