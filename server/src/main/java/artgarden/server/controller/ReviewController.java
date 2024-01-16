package artgarden.server.controller;

import artgarden.server.entity.Review;
import artgarden.server.entity.dto.reviewDto.ReviewDto;
import artgarden.server.entity.dto.reviewDto.ReviewUpdateDto;
import artgarden.server.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Review", description = "리뷰 조회 API")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "전체 리뷰 조회", description = "/reviews")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview(){
        List<Review> reviews = reviewService.getAllReview();
        System.out.println("전체 리뷰 조회");
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "공연별 리뷰 조회", description = "/reviews/PF123456")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/reviewList/{performId}")
    public ResponseEntity<List<Review>> getAllReviewByPerformId(@PathVariable String performId){
        List<Review> reviews = reviewService.getAllReviewByPerformId(performId);
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "상세 리뷰 조회", description = "/reviews/1")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id){
        Review review = reviewService.getReview(id);
        return ResponseEntity.ok(review);
    }

    @Operation(summary = "리뷰 등록", description = "/reviews")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@RequestBody ReviewDto review){
        reviewService.createReview(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "리뷰 수정", description = "/reviews/1")
    @ApiResponse(responseCode = "200", description = "성공")
    @PatchMapping("/reviews/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id,@RequestBody ReviewUpdateDto review){
        reviewService.updateReview(id, review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "리뷰 삭제", description = "/reviews/1")
    @ApiResponse(responseCode = "200", description = "성공")
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
