package artgarden.server.exhibit;

import artgarden.server.exhibit.entity.dto.ExhibitPageDTO;
import artgarden.server.exhibit.service.ExhibitService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        @RequestParam(defaultValue = "30") int size){

        Pageable pageable = PageRequest.of(page-1, size);
        ExhibitPageDTO exhibits = exhibitService.getExhibits(keyword, days, pageable);

        return ResponseEntity.ok(exhibits);
    }
}
