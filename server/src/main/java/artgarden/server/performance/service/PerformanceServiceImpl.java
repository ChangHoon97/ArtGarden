package artgarden.server.performance.service;

import artgarden.server.common.entity.dto.PageDTO;
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

    public PageDTO<PerformanceListDTO> getPerformances(String keyword, String status, int days, Pageable pageable, String[] searchAreaArr, String orderby, String memberid){
        PerformancePageDTO data = new PerformancePageDTO();
        Page<Performance> performances = null;
        Page<PerformanceListDTO> performances2 = null;

        LocalDate expectDate = LocalDate.now().plusDays(days);

        if(orderby.equals("popular")){
            performances = performanceRepository.getPopularPerformances(keyword, status, expectDate, pageable, searchAreaArr);
        } else if(orderby.equals("scrap")){
            performances = performanceRepository.getScrapPerformances(keyword, status, expectDate, pageable, searchAreaArr);
        } else{
            performances2 = performanceRepository.getLatestPerformances2(keyword, status, expectDate, pageable, searchAreaArr, memberid);
        }

        PageDTO<PerformanceListDTO> dto = new PageDTO(performances2.getNumber()+1, performances2.getTotalPages(), performances2.getSize(), performances2.getTotalElements(), performances2.hasNext(), performances2.getContent());
        //DTO 변환 과정
        /*for(Performance performance : performances.getContent()){
            data.getData().add(new PerformanceListDTO(performance));
        }

        data.setPageNo(performances.getNumber()+1);
        data.setTotalPages(performances.getTotalPages());
        data.setPageSize(performances.getSize());
        data.setTotalElements(performances.getTotalElements());
        data.setHasNext(performances.hasNext());*/

        return dto;
    }

    @Override
    @Transactional
    public String updateScrapCnt(String id, String status) {
        String result = "ProcessSuccess";
        if(status.equals("ASC")){
            performanceRepository.updateScrapCntASC(id);
        } else if(status.equals("DESC")){
            performanceRepository.updateScrapCntDESC(id);
        } else{
            result = "ProcessFail";
        }
        return result;
    }

}
