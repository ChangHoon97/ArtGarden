package artgarden.server.service;

import artgarden.server.entity.Performance;
import artgarden.server.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    public List<Performance> findAll() {
        return performanceRepository.findAllByOrderByEndDateAsc();
    }

    public Performance findById(String id){

        return performanceRepository.findById(id);
    }
}
