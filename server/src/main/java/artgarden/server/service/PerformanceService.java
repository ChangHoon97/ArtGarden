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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    public Performance findById(String id){

        return performanceRepository.findById(id);
    }

    public Page<Performance> getPerformances(String keyword, String status, int startDate, Pageable pageable){
        LocalDate expectDate = LocalDate.now().plusDays(startDate);
        return performanceRepository.getPerformances(keyword, status, expectDate, pageable);
    }
}
