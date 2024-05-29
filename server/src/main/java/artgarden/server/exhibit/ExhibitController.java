package artgarden.server.exhibit;

import artgarden.server.exhibit.entity.Exhibit;
import artgarden.server.exhibit.entity.dto.ExhibitDetailDTO;
import artgarden.server.exhibit.entity.dto.ExhibitPageDTO;
import artgarden.server.exhibit.service.ExhibitService;
import artgarden.server.performance.entity.Performance;
import artgarden.server.performance.entity.dto.PerformanceDetailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Exhibit", description = "전시 정보 조회 API")
public class ExhibitController {
    private final ExhibitService exhibitService;

    @GetMapping("/exhibits")
    public ResponseEntity<ExhibitPageDTO> getExhibits(
        @Parameter(description = "제목 검색 키워드")
        @RequestParam(defaultValue = "") String keyword,
        @Parameter(description = "전시 날짜(일), 오늘 ~ 오늘+days(일) 기간 검색")
        @RequestParam(defaultValue = "30") int days,
        @Parameter(description = "표시할 페이지")
        @RequestParam(defaultValue = "1") int page,
        @Parameter(description = "한 페이지에 볼 게시물 수")
        @RequestParam(defaultValue = "30") int size,
        @Parameter(description = "정렬조건(latest(기본값), popular, scrap")
        @RequestParam(defaultValue = "latest") String orderby){

        Pageable pageable = PageRequest.of(page-1, size);
        ExhibitPageDTO exhibits = exhibitService.getExhibits(keyword, days, pageable, orderby);

        return ResponseEntity.ok(exhibits);
    }

    @Operation(summary = "전시 상세 조회", description = "/exhibits/123456")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/exhibits/{id}")
    public ResponseEntity<ExhibitDetailDTO> getPerformance(@PathVariable String id){

        Exhibit exhibit = exhibitService.selectExhibit(id);

        //null일때 예외처리
        if(exhibit == null){
            return ResponseEntity.notFound().build();
        }

        ExhibitDetailDTO dto = new ExhibitDetailDTO(exhibit);

        return ResponseEntity.ok(dto);
    }
}
