package artgarden.server.rank.repository;

import artgarden.server.rank.entity.WeeklyRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RankRepository extends JpaRepository<WeeklyRank, Long> {

    WeeklyRank findByRankDate(LocalDate rankDate);
}
