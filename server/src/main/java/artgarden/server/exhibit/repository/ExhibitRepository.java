package artgarden.server.exhibit.repository;

import artgarden.server.exhibit.entity.Exhibit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {

    @Query("SELECT e " +
            "FROM Exhibit e " +
            "WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate " +
            "ORDER BY e.exstatus, e.visitcnt DESC")
    Page<Exhibit> getExhibitsPopular(@Param("keyword") String keyword,
                              @Param("expectDate") LocalDate expectDate,
                              Pageable pageable);

    @Query("SELECT e " +
            "FROM Exhibit e " +
            "WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate " +
            "ORDER BY e.exstatus, e.enddate")
    Page<Exhibit> getExhibitsLatest(@Param("keyword") String keyword,
                                 @Param("expectDate") LocalDate expectDate,
                                 Pageable pageable);

    @Query("SELECT e " +
            "FROM Exhibit e " +
            "WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate " +
            "ORDER BY e.exstatus, e.scrapcnt DESC")
    Page<Exhibit> getExhibitsScrap(@Param("keyword") String keyword,
                                   @Param("expectDate") LocalDate expectDate,
                                   Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Exhibit " +
            "set exstatus = " +
            "case " +
            "when now() between startdate and enddate then '전시중' " +
            "when now() > enddate then '전시종료' " +
            "when now() < startdate then '전시예정' " +
            "end, " +
            "areacd = (select c.code from Code c where c.cdnm = area), " +
            "genrecd = (select c.code from Code c where c.cdnm = genre)")
    void updateCode();

}
