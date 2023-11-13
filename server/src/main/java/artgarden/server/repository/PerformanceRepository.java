package artgarden.server.repository;

import artgarden.server.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    List<Performance> findAllByOrderByEndDateAsc();

    Performance findById(String id);
}
