package artgarden.server.rank;

import artgarden.server.performance.entity.Performance;
import artgarden.server.rank.entity.WeeklyRank;
import artgarden.server.rank.entity.dto.RankListDto;
import artgarden.server.kopis.service.KopisServiceImpl;
import artgarden.server.performance.service.PerformanceServiceImpl;
import artgarden.server.rank.service.RankService;
import artgarden.server.rank.service.RankServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@Tag(name = "Rank", description = "공연 순위 조회 API")
public class RankController {

    private final RankService rankService;
    private final PerformanceServiceImpl performanceService;
    private final KopisServiceImpl kopisService;

    @Operation(summary = "공연 순위 조회", description = "/ranks/20231114")
    @GetMapping("/ranks/{rankDate}")
    public ResponseEntity<?> getRank(
            @Parameter(description = "조회 날짜, rankDate-7 ~ rankDate 기간의 순위 조회")
            @PathVariable String rankDate,
            @Parameter(description = "랭킹 몇위까지 조회 할 것인지(최대 10, 기본 10)")
            @RequestParam(defaultValue = "10") int size){
        LocalDate dates = StringToLocalDate(rankDate);
        WeeklyRank weeklyRank = new WeeklyRank();
        weeklyRank = rankService.findByRankDate(dates);

        List<String> performIds = new ArrayList<>();
        List<RankListDto> dtoList = new ArrayList<>();
        if(weeklyRank.getPerformId() != null) {
            performIds = weeklyRank.getPerformId();

            if(size > 10){
                size = 10;
            }

            for(int i=0; i<size; i++){     // FE요청사항 : 응답 데이터 9개로 해주세요
                String performId = performIds.get(i);
                if(performanceService.findById(performId) == null){
                    kopisService.saveSinglePerformance(performId);
                }
                Performance performance = performanceService.findById(performId);
                RankListDto dto = new RankListDto(performance, performIds.indexOf(performId)+1);
                dtoList.add(dto);
            }

        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No.Content");
        }
        return ResponseEntity.ok(dtoList);
    }

    private LocalDate StringToLocalDate(String rankDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(rankDate, formatter);
    }
}
