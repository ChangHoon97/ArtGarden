package artgarden.server.performance.service;

import artgarden.server.performance.entity.Performance;
import artgarden.server.performance.entity.dto.PerformancePageDTO;
import org.springframework.data.domain.Pageable;

public interface PerformanceService {

    public Performance findById(String id);
    public PerformancePageDTO getPerformances(String keyword, String status, int days, Pageable pageable, String[] searchAreaArr, String orderby);
}
