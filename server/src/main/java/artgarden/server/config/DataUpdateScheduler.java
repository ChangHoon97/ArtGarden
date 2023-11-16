package artgarden.server.config;

import artgarden.server.service.KopisService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataUpdateScheduler {

    private final KopisService kopisService;

    @Scheduled(cron = "0 0 10 * * ?", zone = "Asia/Seoul")
    public void updateData(){
        kopisService.updatePerformance();
    }
}
