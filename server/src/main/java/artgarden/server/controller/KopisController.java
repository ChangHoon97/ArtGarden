package artgarden.server.controller;

import artgarden.server.entity.Performance;
import artgarden.server.service.KopisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class KopisController {

    private final KopisService kopisService;

    //초기 DB에 전체 데이터 저장(06/01~11/08)
    @GetMapping("/api")
    public ResponseEntity<String> kopisAPISave(){

        kopisService.apiSave();

        return ResponseEntity.ok("Data save successfully");
    }

    @GetMapping("/performances")
    public ResponseEntity<List<Performance>> getAllList(){
        List<Performance> performanceList = kopisService.findAll();
        return ResponseEntity.ok(performanceList);
    }

    @GetMapping("/performances/{id}")
    public ResponseEntity<Optional<Performance>> getPerformance(@PathVariable String id){

        Optional<Performance> performance = kopisService.findById(id);

        return ResponseEntity.ok(performance);
    }



}
