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
import java.util.Optional;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {

    @Query("SELECT e " +
            "FROM Exhibit e " +
            "LEFT OUTER JOIN Code c ON c.cdnm = e.exstatus " +
            "WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate " +
            "ORDER BY c.orderby, e.visitcnt DESC")
    Page<Exhibit> getExhibitsPopular(@Param("keyword") String keyword,
                              @Param("expectDate") LocalDate expectDate,
                              Pageable pageable);

    @Query("SELECT e " +
            "FROM Exhibit e " +
            "LEFT OUTER JOIN Code c ON c.cdnm = e.exstatus " +
            "WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate " +
            "ORDER BY c.orderby, e.enddate")
    Page<Exhibit> getExhibitsLatest(@Param("keyword") String keyword,
                                 @Param("expectDate") LocalDate expectDate,
                                 Pageable pageable);

    @Query("SELECT e " +
            "FROM Exhibit e " +
            "LEFT OUTER JOIN Code c ON c.cdnm = e.exstatus " +
            "WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate " +
            "ORDER BY c.orderby, e.scrapcnt DESC")
    Page<Exhibit> getExhibitsScrap(@Param("keyword") String keyword,
                                   @Param("expectDate") LocalDate expectDate,
                                   Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Exhibit e " +
            "set e.exstatus = " +
            "case " +
            "when :today >= e.startdate and CURRENT_DATE <= e.enddate then '전시중' " +
            "when :today > e.enddate then '전시종료' " +
            "when :today < e.startdate then '전시예정' " +
            "end ")
    void updateExStatus(LocalDate today);


    @Transactional
    @Modifying
    @Query("UPDATE Exhibit e " +
            "SET e.areacd = (SELECT c.code FROM Code c WHERE e.area = c.cdnm) " +
            "WHERE e.areacd is null")
    void updateAreaCode();

    @Transactional
    @Modifying
    @Query("UPDATE Exhibit e " +
            "SET e.genrecd = (SELECT c.code FROM Code c WHERE e.genre = c.cdnm) " +
            "WHERE e.genrecd is null")
    void updateGenreCode();

    @Query("SELECT e " +
            "FROM Exhibit e " +
            "WHERE e.id = :id")
    Exhibit selectExhibit(@Param("id") String id);

}
