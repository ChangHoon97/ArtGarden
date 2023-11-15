package artgarden.server.repository;

import artgarden.server.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RankRepository extends JpaRepository<Rank, Long> {

    List<Rank> findAllByRankDate(LocalDate rankDate);
}
