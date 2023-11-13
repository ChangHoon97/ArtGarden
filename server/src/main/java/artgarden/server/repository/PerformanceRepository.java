package artgarden.server.repository;

import artgarden.server.entity.Performance;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    List<Performance> findAllByOrderByEndDateAsc();

    Performance findById(String id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Performance e WHERE e.endDate <= :deleteDateStandard")
    void deleteByEndDateBefore(LocalDate deleteDateStandard);

    @Transactional
    @Modifying
    @Query("UPDATE Performance e SET e.perform_status = '공연완료' WHERE e.endDate <= :today AND e.perform_status <> '공연완료'")
    void updatePerformStatusForExpiredPerformances(LocalDate today);

}
