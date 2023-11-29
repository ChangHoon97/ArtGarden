package artgarden.server.controller;

import artgarden.server.entity.Review;
import artgarden.server.entity.dto.reviewDto.ReviewDto;
import artgarden.server.entity.dto.reviewDto.ReviewUpdateDto;
import artgarden.server.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview(){
        List<Review> reviews = reviewService.getAllReview();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/reviewList/{performId}")
    public ResponseEntity<List<Review>> getAllReviewByPerformId(@PathVariable String performId){
        List<Review> reviews = reviewService.getAllReviewByPerformId(performId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@RequestBody ReviewDto review){
        reviewService.createReview(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/reviews/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id,@RequestBody ReviewUpdateDto review){
        reviewService.updateReview(id, review);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id){
        Review review = reviewService.getReview(id);
        return ResponseEntity.ok(review);
    }
}
