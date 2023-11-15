package artgarden.server.controller;

import artgarden.server.service.KopisService;
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
@Tag(name = "KopisApi", description = "Kopis OpenApi를 활용한 API")
public class KopisController {

    private final KopisService kopisService;

    //초기 DB에 전체 데이터 저장(06/01~11/09)
    @GetMapping("/manual/update/upcoming")
    public ResponseEntity<String> manualUpcomingUpdate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String standardDate = LocalDate.now().plusMonths(1).format(formatter);

        kopisService.updateUpcoming(standardDate);

        return ResponseEntity.ok("Data save successfully");
    }

    @GetMapping("/manual/update/ongoing")
    public ResponseEntity<String> manualOngoingUpdate(){

        kopisService.updateOngoing();

        return ResponseEntity.ok("Data update successfully");
    }

    @GetMapping("/manual/update/rank/{rankDate}")
    public ResponseEntity<String> manualRankUpdate(@PathVariable String rankDate){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String standardDate = LocalDate.now().minusDays(1).format(formatter);
        kopisService.updateRank("week",rankDate);

        return ResponseEntity.ok("Data update successfully");
    }




}
