package artgarden.server.performance.service;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.performance.entity.Performance;
import artgarden.server.performance.entity.dto.PerformanceDetailDTO;
import artgarden.server.performance.entity.dto.PerformanceListDTO;
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

    @Transactional
    public PerformanceDetailDTO getPerformanceDetail(String id, String memberid){
        PerformanceDetailDTO dto = performanceRepository.getPerformanceDetail(id, memberid);
        performanceRepository.updateVisitCnt(id);

        return dto;
    }

    public PageDTO<PerformanceListDTO> getPerformances(String keyword, String status, int days, Pageable pageable, String[] searchAreaArr, String orderby, String memberid){
        Page<PerformanceListDTO> performances = null;

        LocalDate expectDate = LocalDate.now().plusDays(days);

        if(orderby.equals("popular")){
            performances = performanceRepository.getPopularPerformances(keyword, status, expectDate, pageable, searchAreaArr,memberid);
        } else if(orderby.equals("scrap")){
            performances = performanceRepository.getScrapPerformances(keyword, status, expectDate, pageable, searchAreaArr,memberid);
        } else if(orderby.equals("latest")){
            performances = performanceRepository.getLatestPerformances(keyword, status, expectDate, pageable, searchAreaArr, memberid);
        }

        assert performances != null;
        PageDTO<PerformanceListDTO> dto = new PageDTO<>(performances);

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
