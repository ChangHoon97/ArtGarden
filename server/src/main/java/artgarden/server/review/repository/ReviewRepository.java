package artgarden.server.review.repository;

import artgarden.server.review.entity.Review;
import artgarden.server.review.entity.dto.ReviewListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT new artgarden.server.review.entity.dto.ReviewListDTO(r, COALESCE(p.posterurl,e.posterurl), COALESCE(p.name, e.name), COALESCE(p.genre, e.genre)) " +
            "FROM Review r " +
            "LEFT OUTER JOIN Performance p on p.id = :object_id " +
            "LEFT OUTER JOIN Exhibit e on e.id = :object_id " +
            "WHERE r.objectid = :object_id")
    Page<ReviewListDTO> findAllByObjectId(@Param("object_id") String object_id, Pageable pageable);



    @Query("SELECT new artgarden.server.review.entity.dto.ReviewListDTO(r, COALESCE(p.posterurl,e.posterurl), COALESCE(p.name, e.name), COALESCE(p.genre, e.genre)) " +
            "FROM Review r " +
            "LEFT OUTER JOIN Performance p on p.id = r.objectid " +
            "LEFT OUTER JOIN Exhibit e on e.id = r.objectid " +
            "ORDER BY r.regdt DESC")
    Page<ReviewListDTO> findALlPage(Pageable pageable);
}
