package artgarden.server.rank.service;

import artgarden.server.rank.entity.WeeklyRank;
import artgarden.server.rank.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankServiceImpl implements RankService{
    private final RankRepository rankRepository;

    public WeeklyRank findByRankDate(LocalDate rankDate){
        return rankRepository.findByRankDate(rankDate);
    }
}
