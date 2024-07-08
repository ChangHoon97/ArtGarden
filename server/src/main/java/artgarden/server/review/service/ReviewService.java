package artgarden.server.review.service;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.review.entity.Review;
import artgarden.server.review.entity.dto.ReviewDTO;
import artgarden.server.review.entity.dto.ReviewListDTO;
import artgarden.server.review.entity.dto.ReviewUpdateDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    public PageDTO<ReviewListDTO> getAllReview(Pageable pageable);

    public void createReview(ReviewDTO dto);

    public String updateReview(HttpServletRequest request, Long id, ReviewUpdateDto dto);

    public String deleteReview(HttpServletRequest request, Long id);

    PageDTO<ReviewListDTO> getAllReviewByObjectId(String id, Pageable pageable);

    Review getReview(Long id);
}
