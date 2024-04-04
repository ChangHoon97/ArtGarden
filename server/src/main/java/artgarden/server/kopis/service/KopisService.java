package artgarden.server.kopis.service;

import artgarden.server.performance.entity.Performance;

import java.time.LocalDate;
import java.util.List;

public interface KopisService {

    public void updateUpcoming(String standardDate);

    public void updateOngoing();

    public void deletePerformed(LocalDate standardDate);

    public void updatePerformStatus();

    public void updateRank(String ststype, String rankDate);

    public void saveSinglePerformance(String performId);

    public void updateAreaCode();

    public void updateGenreCode();

    public List<Performance> getPerformanceList(String startDate, String endDate, String performStatus);

}
