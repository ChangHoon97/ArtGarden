package artgarden.server.performance.repository;

import artgarden.server.performance.entity.Performance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    Page<Performance> findByPerformstatus(String performstatus, Pageable pageable);

    Performance findById(String id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Performance e " +
            "WHERE e.enddate <= :deleteDateStandard")
    void deleteByEndDateBefore(LocalDate deleteDateStandard);

    @Transactional
    @Modifying
    @Query("UPDATE Performance e SET e.performstatus = '공연완료' " +
            "WHERE e.enddate <= :today AND e.performstatus <> '공연완료'")
    void updatePerformStatusForExpiredPerformances(LocalDate today);

    @Query("SELECT e" +
            " FROM Performance e" +
            " LEFT OUTER JOIN Code c ON c.cdnm = e.performstatus" +
            " WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate" +
            " AND (:status = 'all' OR e.performstatus = :status)" +
            " AND (:searchAreaArr IS NULL OR e.areacd IN :searchAreaArr) " +
            " ORDER BY c.orderby, e.enddate")
    Page<Performance> getLatestPerformances(@Param("keyword") String keyword,
                                      @Param("status") String status,
                                      @Param("expectDate") LocalDate expectDate,
                                      Pageable pageable,
                                      @Param("searchAreaArr") String[] searchAreaArr);

    @Query("SELECT e" +
            " FROM Performance e" +
            " LEFT OUTER JOIN Code c ON c.cdnm = e.performstatus" +
            " WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate" +
            " AND (:status = 'all' OR e.performstatus = :status)" +
            " AND (:searchAreaArr IS NULL OR e.areacd IN :searchAreaArr)" +
            " ORDER BY c.orderby, e.visitcnt desc")
    Page<Performance> getPopularPerformances(@Param("keyword") String keyword,
                                            @Param("status") String status,
                                            @Param("expectDate") LocalDate expectDate,
                                            Pageable pageable,
                                            @Param("searchAreaArr") String[] searchAreaArr);

    @Query("SELECT e" +
            " FROM Performance e" +
            " LEFT OUTER JOIN Code c ON c.cdnm = e.performstatus" +
            " WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate" +
            " AND (:status = 'all' OR e.performstatus = :status)" +
            " AND (:searchAreaArr IS NULL OR e.areacd IN :searchAreaArr)" +
            " ORDER BY c.orderby, e.scrapcnt desc")
    Page<Performance> getScrapPerformances(@Param("keyword") String keyword,
                                             @Param("status") String status,
                                             @Param("expectDate") LocalDate expectDate,
                                             Pageable pageable,
                                             @Param("searchAreaArr") String[] searchAreaArr);

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

    @Modifying
    @Transactional
    @Query("UPDATE Performance p SET p.scrapcnt = p.scrapcnt-1 WHERE p.id = :performid")
    void updateScrapCntDESC(@Param("performid") String id);

    @Modifying
    @Transactional
    @Query("UPDATE Performance p SET p.scrapcnt = p.scrapcnt+1 WHERE p.id = :performid")
    void updateScrapCntASC(@Param("performid") String id);


}
