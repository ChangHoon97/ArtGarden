package artgarden.server.review.service;


import artgarden.server.review.entity.Review;
import artgarden.server.review.entity.dto.ReviewDto;
import artgarden.server.review.entity.dto.ReviewListDto;
import artgarden.server.review.entity.dto.ReviewUpdateDto;
import artgarden.server.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    public List<Review> getAllReview(){
        return reviewRepository.findAll();
    }

    public Page<ReviewListDto> getAllReviewByPerformId(String id, Pageable pageable){
        return reviewRepository.findAllByPerformid(id, pageable);
    }

    public Review getReview(Long id){
        Optional<Review> review = reviewRepository.findById(id);

        return review.orElseThrow();
    }

    @Transactional
    public void createReview(ReviewDto dto){
        dto.setRegdt(LocalDateTime.now());
        Review review = new Review();
        review.createFromDto(dto);

        reviewRepository.save(review);
    }

    @Transactional
    public void updateReview(Long id, ReviewUpdateDto dto){
        dto.setUpddt(LocalDateTime.now());
        Review review = reviewRepository.findById(id).orElseThrow();
        review.updateFromDto(dto);

        reviewRepository.save(review);

    }

    @Transactional
    public void deleteReview(Long id){
        reviewRepository.deleteById(id);
    }
}
