package artgarden.server.kopis.service;

import artgarden.server.performance.entity.Performance;

import java.time.LocalDate;
import java.util.List;

public interface KopisService {

    public void updatePerformance(String startDate, String endDate, String performStatus);

    public void updateEndPerformStatus();

    public void updateRank(String ststype, String rankDate);

    public void saveSinglePerformance(String performId);

    public void updateAreaCode();

    public void updateGenreCode();

    public List<Performance> getPerformanceList(String startDate, String endDate, String performStatus);

}
