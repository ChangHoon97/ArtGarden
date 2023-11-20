package artgarden.server.service;


import artgarden.server.entity.Review;
import artgarden.server.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    @Transactional
    public void createReview(Review review){
        reviewRepository.save(review);
    }

    @Transactional
    public void updateReview(Review review){

    }

    public Optional<Review> getReview(Long id){
        return reviewRepository.findById(id);
    }

    public List<Review> getAllReview(){
        return reviewRepository.findAll();
    }
}
