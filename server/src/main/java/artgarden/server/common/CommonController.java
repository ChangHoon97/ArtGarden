package artgarden.server.common;

import artgarden.server.common.entity.Code;
import artgarden.server.common.entity.dto.CodeDTO;
import artgarden.server.common.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name="Util", description = "유틸 API")
@RequestMapping("/common")
public class CommonController {

    private final CommonService commonService;

    @Operation(summary = "ec2 대상그룹 health check")
    @GetMapping("/healthcheck")
    public ResponseEntity<String> home(){
        String result = "ok";
        String result1 = "ok";
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "코드 리스트")
    @GetMapping("/codeList")
    public ResponseEntity<CodeDTO> codeList(@RequestParam String cdtype, @RequestParam String uppcd, @RequestParam Integer cddepth ){
        return ResponseEntity.status(HttpStatus.OK).body(commonService.getCodeList(cdtype, uppcd, cddepth));
    }
}
