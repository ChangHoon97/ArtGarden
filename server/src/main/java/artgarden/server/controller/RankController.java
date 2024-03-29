package artgarden.server.controller;

import artgarden.server.entity.Performance;
import artgarden.server.entity.WeeklyRank;
import artgarden.server.entity.dto.rankDto.RankListDto;
import artgarden.server.service.KopisService;
import artgarden.server.service.PerformanceService;
import artgarden.server.service.RankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Rank", description = "공연 순위 조회 API")
public class RankController {

    private final RankService rankService;
    private final PerformanceService performanceService;
    private final KopisService kopisService;

    @Operation(summary = "공연 순위 조회", description = "/ranks/20231114")
    @GetMapping("/ranks/{rankDate}")
    public ResponseEntity<List<RankListDto>> getRank(
            @Parameter(description = "조회 날짜, rankDate-7 ~ rankDate 기간의 순위 조회")
            @PathVariable String rankDate){
        LocalDate dates = StringToLocalDate(rankDate);
        WeeklyRank weeklyRank = rankService.findByRankDate(dates);

        List<String> performIds = weeklyRank.getPerformId();
        List<RankListDto> dtoList = new ArrayList<>();

        for(String performId : performIds){
            if(performanceService.findById(performId) == null){
                kopisService.saveSinglePerformance(performId);
            }
            Performance performance = performanceService.findById(performId);
            RankListDto dto = new RankListDto(performance, performIds.indexOf(performId)+1);
            dtoList.add(dto);
        }
        return ResponseEntity.ok(dtoList);
    }

    private LocalDate StringToLocalDate(String rankDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(rankDate, formatter);
    }
}
