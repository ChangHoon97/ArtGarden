package artgarden.server.controller;

import artgarden.server.service.KopisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "KopisApi", description = "Kopis OpenApi를 활용하여 수동으로 DB에 정보 저장, 매일 오전 10시 스케쥴링으로 자동 저장됨")
public class KopisController {

    private final KopisService kopisService;

    @Operation(summary = "이후 한달간 공연 예정 상태의 공연 저장", description = "/manual/update/upcoming")
    @GetMapping("/manual/update/upcoming")
    public ResponseEntity<String> manualUpcomingUpdate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String standardDate = LocalDate.now().plusMonths(1).format(formatter);

        kopisService.updateUpcoming(standardDate);

        return ResponseEntity.ok("Data save successfully");
    }

    @Operation(summary = "현재 공연중 상태인 공연 저장", description = "/manual/update/ongoing")
    @GetMapping("/manual/update/ongoing")
    public ResponseEntity<String> manualOngoingUpdate(){

        kopisService.updateOngoing();

        return ResponseEntity.ok("Data save successfully");
    }

    @Operation(summary = "해당날짜로 까지 1주일간의 공연 순위 저장", description = "/manual/update/rank/20231114")
    @GetMapping("/manual/update/rank/{rankDate}")
    public ResponseEntity<String> manualRankUpdate(@PathVariable String rankDate){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String standardDate = LocalDate.now().minusDays(1).format(formatter);
        kopisService.updateRank("week",rankDate);

        return ResponseEntity.ok("Data save successfully");
    }




}
