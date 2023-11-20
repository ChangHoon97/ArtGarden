package artgarden.server.controller;

import artgarden.server.entity.Review;
import artgarden.server.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview(){
        List<Review> reviews = reviewService.getAllReview();
        return ResponseEntity.ok(reviews);
    }


    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id){
        Optional<Review> optionalReview = reviewService.getReview(id);
        if(optionalReview.isPresent()){
            Review review = optionalReview.get();
            return ResponseEntity.ok(review);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
