package artgarden.server.controller;

import artgarden.server.entity.Performance;
import artgarden.server.entity.dto.performanceDto.PerformanceDetailDto;
import artgarden.server.entity.dto.performanceDto.PerformanceListDto;
import artgarden.server.service.KopisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class KopisController {

    private final KopisService kopisService;

    //초기 DB에 전체 데이터 저장(06/01~11/09)
    @GetMapping("/api")
    public ResponseEntity<String> kopisApiSave(){

        kopisService.apiSave();

        return ResponseEntity.ok("Data save successfully");
    }

    @GetMapping("/updateDB")
    public ResponseEntity<String> kopisApiUpdate(){

        kopisService.updatePerformance();

        return ResponseEntity.ok("Data update successfully");
    }

    @GetMapping("/performances")
    public ResponseEntity<List<PerformanceListDto>> getAllList(){
        List<PerformanceListDto> dtoList= new ArrayList<>();
        PerformanceListDto dto = new PerformanceListDto();

        List<Performance> performanceList = kopisService.findAll();
        for(Performance performance : performanceList){
            dtoList.add(dto.fromEntity(performance));
        }
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/performances/{id}")
    public ResponseEntity<PerformanceDetailDto> getPerformance(@PathVariable String id){

        PerformanceDetailDto dto = new PerformanceDetailDto();

        Performance performance = kopisService.findById(id);

        //null일때 예외처리
        if(performance == null){
            return ResponseEntity.notFound().build();
        }

        dto.fromEntity(performance);

        return ResponseEntity.ok(dto);
    }




}
