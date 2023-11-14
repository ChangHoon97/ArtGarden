package artgarden.server.service;

import artgarden.server.entity.Rank;
import artgarden.server.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankService {
    private final RankRepository rankRepository;

    public Rank findByRankDate(LocalDate rankDate){
        return rankRepository.findByRankDate(rankDate);
    }
}
