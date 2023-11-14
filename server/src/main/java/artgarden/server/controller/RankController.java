package artgarden.server.controller;

import artgarden.server.entity.Performance;
import artgarden.server.entity.Rank;
import artgarden.server.entity.dto.performanceDto.PerformanceListDto;
import artgarden.server.service.PerformanceService;
import artgarden.server.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;
    private final PerformanceService performanceService;

    @GetMapping("/ranks/{rankDate}")
    public ResponseEntity<List<PerformanceListDto>> getRank(@PathVariable String rankDate){
        LocalDate dates = StringToLocalDate(rankDate);
        Rank rank = rankService.findByRankDate(dates);
        List<String> performIds = rank.getPerformIds();
        List<PerformanceListDto> dtoList= new ArrayList<>();
        PerformanceListDto dto = new PerformanceListDto();
        for(String performId : performIds){
            Performance performance = performanceService.findById(performId);
            dtoList.add(dto.fromEntity(performance));
        }
        return ResponseEntity.ok(dtoList);
    }

    private LocalDate StringToLocalDate(String rankDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(rankDate, formatter);
    }
}
