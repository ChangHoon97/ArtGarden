package artgarden.server.scrap;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.scrap.entity.dto.ScrapMyDTO;
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
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getScrapByMember(@Parameter(description = "표시할 페이지")
                                                         @RequestParam(defaultValue = "1") int page,
                                                         @Parameter(description = "한 페이지에 볼 게시물 수")
                                                         @RequestParam(defaultValue = "8") int size,
                                                         HttpServletRequest request){
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");
        PageDTO<ScrapMyDTO> scrap = null;
        Pageable pageable = PageRequest.of(page-1, size);
        if(memberid != null){
            scrap = scrapService.selectMyScrapList(memberid, pageable);
        } else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Requried.Login");
        }

        return ResponseEntity.ok(scrap);
    }

    @GetMapping("/scrapYN")
    public ResponseEntity<?> getScrapYN(@Parameter(description = "공연/전시/팝업 ID")
                                             @RequestParam String objectid,
                                             HttpServletRequest request){
        String result = "";
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");
        if(memberid != null){
            result = scrapService.selectScrapByObjectid(memberid, objectid);
        } else{
            result = "Required.Login";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        return ResponseEntity.ok(result);
    }

    //스크랩하기
    @PostMapping("/scraps")
    public ResponseEntity<?> updateScraping(@Parameter(description = "공연/전시/팝업 ID")
                                                 @RequestBody ScrapingDTO dto,
                                                 HttpServletRequest request){
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");
        String sessionid = (String) session.getId();
        Boolean isNew = session.isNew();
        String result = "ProcessFail";
        if(memberid != null){
            result = scrapService.updateScraping(memberid, dto);
        } else{
            result = "Required.Login";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        return ResponseEntity.ok(result);
    }


}
