package artgarden.server.repository;

import artgarden.server.entity.WeeklyRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RankRepository extends JpaRepository<WeeklyRank, Long> {

    WeeklyRank findByRankDate(LocalDate rankDate);
}
