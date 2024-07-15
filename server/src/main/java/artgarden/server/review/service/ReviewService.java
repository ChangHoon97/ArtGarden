package artgarden.server.review.service;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.review.entity.Review;
import artgarden.server.review.entity.dto.ReviewCreateDTO;
import artgarden.server.review.entity.dto.ReviewListDTO;
import artgarden.server.review.entity.dto.ReviewUpdateDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    public PageDTO<ReviewListDTO> getAllReview(Pageable pageable);

    public void createReview(HttpServletRequest request, ReviewCreateDTO dto);

    public String updateReview(HttpServletRequest request, Long id, ReviewUpdateDTO dto);

    public String deleteReview(HttpServletRequest request, Long id);

    PageDTO<ReviewListDTO> getAllReviewByObjectId(String id, Pageable pageable);

    Review getReview(Long id);
}
