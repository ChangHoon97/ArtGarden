package artgarden.server.culture;

import artgarden.server.culture.service.CultureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "CultureApi", description = "culture.go에서 수동으로 DB에 정보 저장, 매일 오전 00시 스케쥴링으로 자동 저장")
public class CultureController {

    private final CultureService cultureService;

    @Operation(summary = "전시 정보 업데이트")
    @GetMapping("/manual/exhibit")
    public ResponseEntity<String> manualExhibitUpdate() throws Exception {
        String exhibitType = "D000"; //미술 전시

        cultureService.updateExhibitList(exhibitType);

        return ResponseEntity.ok("Data save successfully");
    }

    @Operation(summary = "전시 정보 코드 업데이트")
    @GetMapping("/manual/exhibitcd")
    public ResponseEntity<String> manualExhibitCodeUpdate() {

        cultureService.updateEXStatus();

        return ResponseEntity.ok("Data save successfully");
    }
}
