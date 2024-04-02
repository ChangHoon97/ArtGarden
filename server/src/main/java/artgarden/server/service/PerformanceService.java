package artgarden.server.service;

import artgarden.server.entity.Performance;
import artgarden.server.entity.dto.performanceDto.PerformanceListDTO;
import artgarden.server.entity.dto.performanceDto.PerformancePageDTO;
import artgarden.server.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    public Performance findById(String id){

        return performanceRepository.findById(id);
    }

    public PerformancePageDTO getPerformances(String keyword, String status, int days, Pageable pageable){
        PerformancePageDTO data = new PerformancePageDTO();

        LocalDate expectDate = LocalDate.now().plusDays(days);
        Page<Performance> performances = performanceRepository.getPerformances(keyword, status, expectDate, pageable);

        for(Performance performance : performances.getContent()){
            data.getData().add(new PerformanceListDTO(performance));
        }

        data.setPageNo(performances.getNumber()+1);
        data.setTotalPages(performances.getTotalPages());
        data.setPageSize(performances.getSize());
        data.setTotalElements(performances.getTotalElements());
        data.setHasNext(performances.hasNext());

        return data;
    }

}
