package artgarden.server.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name="기본 컨트롤러")
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> home(){
        String result = "ok";
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
