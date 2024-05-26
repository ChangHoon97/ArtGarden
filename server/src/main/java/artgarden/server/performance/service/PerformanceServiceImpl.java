package artgarden.server.performance.service;

import artgarden.server.performance.entity.Performance;
import artgarden.server.performance.entity.dto.PerformanceListDTO;
import artgarden.server.performance.entity.dto.PerformancePageDTO;
import artgarden.server.performance.repository.PerformanceRepository;
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
public class PerformanceServiceImpl implements PerformanceService{

    private final PerformanceRepository performanceRepository;

    public Performance findById(String id){

        return performanceRepository.findById(id);
    }

    public PerformancePageDTO getPerformances(String keyword, String status, int days, Pageable pageable, String[] searchAreaArr, String orderby){
        PerformancePageDTO data = new PerformancePageDTO();
        Page<Performance> performances = null;

        LocalDate expectDate = LocalDate.now().plusDays(days);

        if(orderby.equals("popular")){
            performances = performanceRepository.getPopularPerformances(keyword, status, expectDate, pageable, searchAreaArr);
        } else if(orderby.equals("scrap")){
            performances = performanceRepository.getScrapPerformances(keyword, status, expectDate, pageable, searchAreaArr);
        } else{
            performances = performanceRepository.getLatestPerformances(keyword, status, expectDate, pageable, searchAreaArr);
        }

        //DTO 변환 과정
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
