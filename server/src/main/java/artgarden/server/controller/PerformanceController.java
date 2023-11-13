package artgarden.server.controller;

import artgarden.server.entity.Performance;
import artgarden.server.entity.dto.performanceDto.PerformanceDetailDto;
import artgarden.server.entity.dto.performanceDto.PerformanceListDto;
import artgarden.server.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping("/performances")
    public ResponseEntity<List<PerformanceListDto>> getAllList(){
        List<PerformanceListDto> dtoList= new ArrayList<>();
        PerformanceListDto dto = new PerformanceListDto();

        List<Performance> performanceList = performanceService.findAll();
        for(Performance performance : performanceList){
            dtoList.add(dto.fromEntity(performance));
        }
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/performances/{id}")
    public ResponseEntity<PerformanceDetailDto> getPerformance(@PathVariable String id){

        PerformanceDetailDto dto = new PerformanceDetailDto();

        Performance performance = performanceService.findById(id);

        //null일때 예외처리
        if(performance == null){
            return ResponseEntity.notFound().build();
        }

        dto.fromEntity(performance);

        return ResponseEntity.ok(dto);
    }
}
