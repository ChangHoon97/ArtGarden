package artgarden.server.performance.service;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.performance.entity.Performance;
import artgarden.server.performance.entity.dto.PerformanceDetailDTO;
import artgarden.server.performance.entity.dto.PerformanceListDTO;
import org.springframework.data.domain.Pageable;

public interface PerformanceService {

    public PerformanceDetailDTO getPerformanceDetail(String id, String memberid);

    public Performance findById(String id);
    public PageDTO<PerformanceListDTO> getPerformances(String keyword, String status, int days, Pageable pageable, String[] searchAreaArr, String orderby, String memberid);

    public String updateScrapCnt(String id, String status);
}
