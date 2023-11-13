package artgarden.server.controller;

import artgarden.server.entity.Performance;
import artgarden.server.entity.dto.performanceDto.PerformanceDetailDto;
import artgarden.server.entity.dto.performanceDto.PerformanceListDto;
import artgarden.server.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping("performances")
    public ResponseEntity<List<PerformanceListDto>> getPerformances(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "all") String status,
            @RequestParam(defaultValue = "30") int startDate,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page-1, size);

        List<PerformanceListDto> dtoList= new ArrayList<>();
        PerformanceListDto dto = new PerformanceListDto();

        List<Performance> performances = performanceService.getPerformances(keyword, status, startDate, pageable).getContent();
        for(Performance performance : performances){
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
