package artgarden.server.review.repository;

import artgarden.server.review.entity.Review;
import artgarden.server.review.entity.dto.ReviewListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r.reviewid as reviewid, r.performid as performid, r.content as content," +
            "r.rate as rate, r.memberid as memberid, r.regid as regid, r.regdt as regdt," +
            "r.updid as updid, r.upddt as upddt," +
            " p.posterurl as posterurl, p.name as name " +
            "FROM Review r INNER JOIN Performance p on p.id = r.performid WHERE r.performid = :perform_id")
    Page<ReviewListDTO> findAllByPerformid(@Param("perform_id") String perform_id, Pageable pageable);
}
