package artgarden.server.scrap;

import artgarden.server.scrap.entity.dto.ScrapPageDTO;
import artgarden.server.scrap.entity.dto.ScrapingDTO;
import artgarden.server.scrap.service.ScrapService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Scrap", description = "찜하기")
public class ScrapController {
    private final ScrapService scrapService;

    //나의 스크랩
    @GetMapping("/myScraps")
    public ResponseEntity<ScrapPageDTO> getScrapByMember(@Parameter(description = "표시할 페이지")
                                                         @RequestParam(defaultValue = "1") int page,
                                                         @Parameter(description = "한 페이지에 볼 게시물 수")
                                                         @RequestParam(defaultValue = "8") int size,
                                                         HttpServletRequest request){
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");
        ScrapPageDTO scrap = new ScrapPageDTO();
        Pageable pageable = PageRequest.of(page-1, size);
        if(memberid != null){
            scrap = scrapService.selectMyScrapList(memberid, pageable);
        }

        return ResponseEntity.ok(scrap);
    }

    //스크랩하기
    @PostMapping("/scraps")
    public ResponseEntity<String> updateScraping(@Parameter(description = "공연/전시ID")
                                                 @RequestBody ScrapingDTO dto,
                                                 HttpServletRequest request){
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");
        String result = "ProcessFail";
        if(memberid != null){
            result = scrapService.updateScraping(memberid, dto);
        }

        return ResponseEntity.ok(result);
    }


}
