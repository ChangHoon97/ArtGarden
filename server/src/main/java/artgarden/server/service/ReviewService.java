package artgarden.server.service;


import artgarden.server.entity.Review;
import artgarden.server.entity.dto.reviewDto.ReviewDto;
import artgarden.server.entity.dto.reviewDto.ReviewUpdateDto;
import artgarden.server.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    @Transactional
    public void createReview(ReviewDto dto){
        dto.setCreated_at(LocalDateTime.now());
        Review review = new Review();
        review.createFromDto(dto);

        reviewRepository.save(review);
    }

    @Transactional
    public void updateReview(ReviewUpdateDto dto){
        dto.setModified_at(LocalDateTime.now());
        Review review = reviewRepository.findById(dto.getId()).get();
        review.updateFromDto(dto);

        reviewRepository.save(review);

    }

    public Optional<Review> getReview(Long id){
        return reviewRepository.findById(id);
    }

    public List<Review> getAllReview(){
        return reviewRepository.findAll();
    }

    public List<Review> getAllReviewByPerformId(String id){
        return reviewRepository.findAllByPerform_id(id);
    }
}
