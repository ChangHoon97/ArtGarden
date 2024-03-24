package artgarden.server.service;

import artgarden.server.entity.Performance;
import artgarden.server.entity.dto.performanceDto.PerformanceListDTO;
import artgarden.server.entity.dto.performanceDto.PerformancePage2DTO;
import artgarden.server.entity.dto.performanceDto.PerformancePageDTO;
import artgarden.server.entity.dto.performanceDto.PerformanceResponseDTO;
import artgarden.server.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    public Performance findById(String id){

        return performanceRepository.findById(id);
    }

    public PerformancePage2DTO getPerformances(String keyword, String status, int days, Pageable pageable){
        PerformancePage2DTO data = new PerformancePage2DTO();

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

    public PerformancePageDTO getInfinitePerformances(String keyword, String status, int days, int size){
        int page = 0;
        boolean hasNext = true;
        Pageable pageable;
        LocalDate expectDate = LocalDate.now().plusDays(days);
        Page<Performance> performances;
        PerformancePageDTO dto = new PerformancePageDTO();

        while(hasNext){
            PerformancePage2DTO dtolist = new PerformancePage2DTO();
            pageable = PageRequest.of(page, size);
            performances = performanceRepository.getPerformances(keyword, status, expectDate, pageable);
            for(Performance performance : performances.getContent()){
                dtolist.getData().add(new PerformanceListDTO(performance));
            }
            dtolist.setPageNo(performances.getNumber()+1);
            dtolist.setTotalPages(performances.getTotalPages());
            dtolist.setPageSize(performances.getSize());
            dtolist.setTotalElements(performances.getTotalElements());
            dtolist.setHasNext(performances.hasNext());

            dto.getPages().add(dtolist);

            hasNext = performances.hasNext();
            page++;
        }

        return dto;
    }
}
