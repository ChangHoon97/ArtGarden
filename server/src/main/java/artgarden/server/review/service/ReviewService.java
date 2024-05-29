package artgarden.server.review.service;

import artgarden.server.review.entity.Review;
import artgarden.server.review.entity.dto.ReviewDTO;
import artgarden.server.review.entity.dto.ReviewPageDTO;
import artgarden.server.review.entity.dto.ReviewUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    public ReviewPageDTO getAllReview(Pageable pageable);

    public void createReview(ReviewDTO dto);

    public void updateReview(Long id, ReviewUpdateDto dto);

    public void deleteReview(Long id);

    ReviewPageDTO getAllReviewByObjectId(String id, Pageable pageable);

    Review getReview(Long id);
}
