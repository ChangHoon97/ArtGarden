package artgarden.server.review.repository;

import artgarden.server.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.perform_id = :perform_id")
    List<Review> findAllByPerform_id(@Param("perform_id") String perform_id);
}
