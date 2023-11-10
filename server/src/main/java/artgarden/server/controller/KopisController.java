package artgarden.server.controller;

import artgarden.server.entity.Performance;
import artgarden.server.entity.TestEntity;
import artgarden.server.service.KopisService;
import artgarden.server.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
@RequiredArgsConstructor
public class KopisController {

    private final KopisService kopisService;
    private final TestService testService;

    @GetMapping("/api")
    public ResponseEntity<String> kopisAPISave(){

        kopisService.apiSave();

        return ResponseEntity.ok("Data save successfully");
    }

    @GetMapping("/test")
    public ResponseEntity<String> testme(){
        TestEntity test = new TestEntity();
        test.setId(1);
        test.setName("창훈");

        testService.testSave(test);

        return ResponseEntity.ok("test success");
    }
}
