package artgarden.server.controller;

import artgarden.server.entity.Performance;
import artgarden.server.entity.dto.performanceDto.PerformanceDetailDto;
import artgarden.server.entity.dto.performanceDto.PerformanceListDto;
import artgarden.server.service.KopisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class KopisController {

    private final KopisService kopisService;

    //초기 DB에 전체 데이터 저장(06/01~11/09)
    @GetMapping("/manualUpcomingUpdate")
    public ResponseEntity<String> manualUpcomingUpdate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String standardDate = LocalDate.now().plusMonths(1).format(formatter);

        kopisService.updateUpcoming(standardDate);

        return ResponseEntity.ok("Data save successfully");
    }

    @GetMapping("/manualOngoingUpdate")
    public ResponseEntity<String> manualOngoingUpdate(){
        log.info("manualOngoingUpdate Start");

        kopisService.updateOngoing();

        return ResponseEntity.ok("Data update successfully");
    }




}
