package artgarden.server.performance;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.performance.entity.Performance;
import artgarden.server.performance.entity.dto.PerformanceDetailDTO;
import artgarden.server.performance.entity.dto.PerformanceListDTO;
import artgarden.server.performance.entity.dto.PerformancePageDTO;
import artgarden.server.performance.service.PerformanceService;
import artgarden.server.performance.service.PerformanceServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Performance", description = "공연 정보 조회 API")
public class PerformanceController {

    private final PerformanceService performanceService;

    @Operation(summary = "공연 목록 조회(검색)", description = "/performances?keyword=키워드&status=공연중&startDate=30&page=1&size=30")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/performances")
    public ResponseEntity<PageDTO> getPerformances(
            @Parameter(description = "제목 검색 키워드")
            @RequestParam(defaultValue = "") String keyword,
            @Parameter(description = "공연 상태(all, 공연완료, 공연중, 공연예정), all은 모든 공연상태")
            @RequestParam(defaultValue = "all") String status,
            @Parameter(description = "공연 날짜(일), 오늘 ~ 오늘+days(일) 기간 검색")
            @RequestParam(defaultValue = "30") int days,
            @Parameter(description = "표시할 페이지")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "한 페이지에 볼 게시물 수")
            @RequestParam(defaultValue = "30") int size,
            @Parameter(description = "지역조건")
            @RequestParam(required = false) String[] searchAreaArr,
            @Parameter(description = "정렬조건(latest(기본값), popular, scrap")
            @RequestParam(defaultValue = "latest") String orderby, HttpServletRequest request){
        Pageable pageable = PageRequest.of(page-1, size);
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");

        PageDTO<PerformanceListDTO> performances = performanceService.getPerformances(keyword, status, days, pageable, searchAreaArr, orderby, memberid);

        return ResponseEntity.ok(performances);
    }

    @Operation(summary = "공연 상세 조회", description = "/performances/PF216230")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/performances/{id}")
    public ResponseEntity<PerformanceDetailDTO> getPerformance(@PathVariable String id){

        PerformanceDetailDTO dto = new PerformanceDetailDTO();

        Performance performance = performanceService.findById(id);

        //null일때 예외처리
        if(performance == null){
            return ResponseEntity.notFound().build();
        }

        dto.fromEntity(performance);

        return ResponseEntity.ok(dto);
    }
}
