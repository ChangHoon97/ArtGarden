package artgarden.server.exhibit.repository;

import artgarden.server.exhibit.entity.Exhibit;
import artgarden.server.exhibit.entity.dto.ExhibitDetailDTO;
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


    @Query("SELECT new artgarden.server.exhibit.entity.dto.ExhibitDetailDTO(e, case when s.scrapyn is null then false else (s.scrapyn= true ) end) " +
            "FROM Exhibit e " +
            "LEFT OUTER JOIN Code c ON c.cdnm = e.status " +
            "LEFT OUTER JOIN Scrap s ON s.objectid = e.id AND s.memberid = :memberid " +
            "WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate " +
            "AND (:searchAreaArr IS NULL OR e.areacd IN :searchAreaArr) " +
            "ORDER BY c.orderby, e.visitcnt DESC")
    Page<ExhibitDetailDTO> getExhibitsPopular(@Param("keyword") String keyword,
                                              @Param("expectDate") LocalDate expectDate,
                                              @Param("searchAreaArr") String[] searchAreaArr,
                                              @Param("memberid") String memberid,
                                              Pageable pageable);

    @Query("SELECT new artgarden.server.exhibit.entity.dto.ExhibitDetailDTO(e, case when s.scrapyn is null then false else (s.scrapyn= true ) end) " +
            "FROM Exhibit e " +
            "LEFT OUTER JOIN Code c ON c.cdnm = e.status " +
            "LEFT OUTER JOIN Scrap s ON s.objectid = e.id AND s.memberid = :memberid " +
            "WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate " +
            "AND (:searchAreaArr IS NULL OR e.areacd IN :searchAreaArr) " +
            "ORDER BY c.orderby, e.enddate")
    Page<ExhibitDetailDTO> getExhibitsLatest(@Param("keyword") String keyword,
                                     @Param("expectDate") LocalDate expectDate,
                                     @Param("searchAreaArr") String[] searchAreaArr,
                                     @Param("memberid") String memberid,
                                     Pageable pageable);

    @Query("SELECT new artgarden.server.exhibit.entity.dto.ExhibitDetailDTO(e, case when s.scrapyn is null then false else (s.scrapyn= true ) end) " +
            "FROM Exhibit e " +
            "LEFT OUTER JOIN Code c ON c.cdnm = e.status " +
            "LEFT OUTER JOIN Scrap s ON s.objectid = e.id AND s.memberid = :memberid " +
            "WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate " +
            "AND (:searchAreaArr IS NULL OR e.areacd IN :searchAreaArr) " +
            "ORDER BY c.orderby, e.scrapcnt DESC")
    Page<ExhibitDetailDTO> getExhibitsScrap(@Param("keyword") String keyword,
                                     @Param("expectDate") LocalDate expectDate,
                                     @Param("searchAreaArr") String[] searchAreaArr,
                                     @Param("memberid") String memberid,
                                     Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Exhibit e " +
            "set e.status = " +
            "case " +
            "when :today >= e.startdate and CURRENT_DATE <= e.enddate then '전시중' " +
            "when :today > e.enddate then '전시종료' " +
            "when :today < e.startdate then '전시예정' " +
            "end ")
    void updateStatus(LocalDate today);


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

    @Query("SELECT new artgarden.server.exhibit.entity.dto.ExhibitDetailDTO(e, case when s.scrapyn is null then false else (s.scrapyn= true ) end) " +
            "FROM Exhibit e " +
            "LEFT OUTER  JOIN Scrap s ON s.objectid = e.id AND s.memberid = :memberid " +
            "WHERE e.id = :id")
    ExhibitDetailDTO selectExhibit(@Param("id") String id, @Param("memberid") String memberid);

    @Modifying
    @Transactional
    @Query("UPDATE Exhibit e SET e.scrapcnt = e.scrapcnt-1 WHERE e.id = :exhibitid")
    void updateScrapCntDESC(@Param("exhibitid") String id);

    @Modifying
    @Transactional
    @Query("UPDATE Exhibit e SET e.scrapcnt = e.scrapcnt+1 WHERE e.id = :exhibitid")
    void updateScrapCntASC(@Param("exhibitid") String id);

}
