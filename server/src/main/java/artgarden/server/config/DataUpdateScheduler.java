package artgarden.server.config;

import artgarden.server.service.KopisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataUpdateScheduler {

    private final KopisService kopisService;

    @Scheduled(cron = "0 0 10 * * ?")
    public void weeklyUpdate(){
        kopisService.updateUpcoming( formatDate(LocalDate.now().plusMonths(1)));    //standard = 한달 이후
        kopisService.deletePerformed(LocalDate.now().minusMonths(1));   //standard = 한달 이전
    }

    @Scheduled(cron = "0 0 12 ? * 2")
    public void dailyUpdate(){
        kopisService.updateOngoing();
        kopisService.updatePerformStatus();
    }

    //LocalDate -> String
    private String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return date.format(formatter);
    }
}
