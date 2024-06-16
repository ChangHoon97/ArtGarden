package artgarden.server.scrap.repository;

import artgarden.server.scrap.entity.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    @Query("SELECT s " +
            "FROM Scrap s " +
            "WHERE s.memberid = :memberid " +
            "ORDER BY s.regdt DESC")
    Page<Scrap> findAllByMemberid(@Param("memberid") String memberid, Pageable pageable);

    @Query("SELECT s " +
            "FROM Scrap s " +
            "WHERE s.memberid = :memberid AND s.objectid = :objectid")
    Scrap findByMemberidAndObjectid(@Param("memberid") String memberid, @Param("objectid") String objectid);

    @Query("UPDATE Scrap s SET s.scrapyn = :scrapyn, s.updid = :memberid, s.upddt = CURRENT_TIMESTAMP " +
            "WHERE s.memberid = :memberid AND s.objectid = :objectid")
    @Modifying
    void updateScrapYN(@Param("memberid") String memberid, @Param("objectid") String objectid, @Param("scrapyn") Boolean scrapyn);
}
