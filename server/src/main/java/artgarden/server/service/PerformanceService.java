package artgarden.server.service;

import artgarden.server.entity.Performance;
import artgarden.server.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    public Page<Performance> findAllPerformanceList(Pageable pageable) {
        return performanceRepository.findAll(pageable);
    }

    public Page<Performance> findOngoingPerformanceList(Pageable pageable){

        return performanceRepository.findByPerformStatus("공연중", pageable);

    }

    public Page<Performance> findExpectPerformanceList(Pageable pageable){
        LocalDate expectDate = LocalDate.now().plusMonths(1);
        return performanceRepository.findByPerformStatusBeforeStartDate("공연예정", expectDate, pageable);
    }


    public Performance findById(String id){

        return performanceRepository.findById(id);
    }
}
