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

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    Page<Performance> findByPerformStatus(String performStatus, Pageable pageable);

    @Query("SELECT e " +
            "FROM Performance e " +
            "WHERE e.startDate BETWEEN CURRENT_DATE AND :expectDate AND e.performStatus = :performStatus")
    Page<Performance> findByPerformStatusBeforeStartDate(@Param("performStatus")String performStatus, @Param("expectDate") LocalDate expectDate, Pageable pageable);

    Performance findById(String id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Performance e " +
            "WHERE e.endDate <= :deleteDateStandard")
    void deleteByEndDateBefore(LocalDate deleteDateStandard);

    @Transactional
    @Modifying
    @Query("UPDATE Performance e SET e.performStatus = '공연완료' " +
            "WHERE e.endDate <= :today AND e.performStatus <> '공연완료'")
    void updatePerformStatusForExpiredPerformances(LocalDate today);

    @Query("SELECT e FROM Performance e WHERE e.name LIKE %:keyword% AND e.startDate < :expectDate" +
            " AND (:status = 'all' OR e.performStatus = :status)")
    Page<Performance> getPerformances(@Param("keyword") String keyword, @Param("status") String status, @Param("expectDate") LocalDate expectDate, Pageable pageable);

    @Modifying
    @Query("UPDATE Performance p " +
            "SET p.areacd = (SELECT c.code FROM Code c WHERE p.area = c.cdnm) " +
            "WHERE p.areacd is null")
    void updateAreaCode();

    @Modifying
    @Query("UPDATE Performance p " +
            "SET p.genrecd = (SELECT c.code FROM Code c WHERE p.genre = c.cdnm) " +
            "WHERE p.genrecd is null")
    void updateGenreCode();


}
