package artgarden.server.service;

import artgarden.server.entity.WeeklyRank;
import artgarden.server.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankService {
    private final RankRepository rankRepository;

    public WeeklyRank findByRankDate(LocalDate rankDate){
        return rankRepository.findByRankDate(rankDate);
    }
}
