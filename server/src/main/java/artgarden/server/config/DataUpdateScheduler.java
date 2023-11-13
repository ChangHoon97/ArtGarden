package artgarden.server.config;

import artgarden.server.service.KopisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataUpdateScheduler {

    private final KopisService kopisService;

    @Scheduled(cron = "0 0 10 * * ?")
    public void weeklyUpdate(){
        kopisService.updateUpcoming();
        kopisService.deletePerformed(LocalDate.now().minusMonths(1));   //한달 이전 정보 삭제
    }

    @Scheduled(cron = "0 0 12 ? * 2")
    public void dailyUpdate(){
        kopisService.updateOngoing();
        kopisService.updatePerformStatus();

    }
}
