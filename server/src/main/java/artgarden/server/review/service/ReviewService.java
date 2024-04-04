package artgarden.server.review.service;

import artgarden.server.review.entity.Review;
import artgarden.server.review.entity.dto.ReviewDto;
import artgarden.server.review.entity.dto.ReviewUpdateDto;

import java.util.List;

public interface ReviewService {

    public List<Review> getAllReview();

    public void createReview(ReviewDto dto);

    public void updateReview(Long id, ReviewUpdateDto dto);

    public void deleteReview(Long id);
}
