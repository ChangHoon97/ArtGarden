package artgarden.server.exhibit.repository;

import artgarden.server.exhibit.entity.Exhibit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {

    @Query("SELECT e " +
            "FROM Exhibit e " +
            "WHERE e.name LIKE %:keyword% AND e.startdate < :expectDate ")
    Page<Exhibit> getExhibits(@Param("keyword") String keyword,
                              @Param("expectDate") LocalDate expectDate,
                              Pageable pageable);
}
