package artgarden.server.scrap;

import artgarden.server.scrap.entity.dto.ScrapPageDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        Pageable pageable = PageRequest.of(page-1, size);
        ScrapPageDTO scrap = scrapService.selectMyScrapList(memberid, pageable);

        return ResponseEntity.ok(scrap);
    }

    //스크랩하기
    @PostMapping("/scraps")
    public ResponseEntity<String> mergeintoScrap(@Parameter(description = "공연/전시ID")
                                                 @RequestParam String objectid,
                                                 HttpServletRequest request){
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");

        String result = "";

        return ResponseEntity.ok(result);
    }


}
