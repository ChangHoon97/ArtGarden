package artgarden.server.repository;

import artgarden.server.entity.Performance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    Page<Performance> findAllByOrderByEndDateAsc(Pageable pageable);
    Page<Performance> findByPerformStatusOrderByEndDateAsc(String performStatus, Pageable pageable);

    @Query("SELECT e FROM Performance e WHERE e.startDate BETWEEN CURRENT_DATE AND :expectDate AND e.performStatus = :performStatus ORDER BY e.startDate ASC ")
    Page<Performance> findByPerformStatusBeforeStartDate(@Param("performStatus")String performStatus, @Param("expectDate") LocalDate expectDate, Pageable pageable);

    Performance findById(String id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Performance e WHERE e.endDate <= :deleteDateStandard")
    void deleteByEndDateBefore(LocalDate deleteDateStandard);

    @Transactional
    @Modifying
    @Query("UPDATE Performance e SET e.performStatus = '공연완료' WHERE e.endDate <= :today AND e.performStatus <> '공연완료'")
    void updatePerformStatusForExpiredPerformances(LocalDate today);

}
