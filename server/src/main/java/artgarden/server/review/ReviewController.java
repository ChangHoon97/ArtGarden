package artgarden.server.review;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.review.entity.Review;
import artgarden.server.review.entity.dto.ReviewDTO;
import artgarden.server.review.entity.dto.ReviewListDTO;
import artgarden.server.review.entity.dto.ReviewUpdateDto;
import artgarden.server.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> getAllReview(
            @Parameter(description = "표시할 페이지")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "한 페이지에 볼 게시물 수")
            @RequestParam(defaultValue = "8") int size){
        Pageable pageable = PageRequest.of(page-1, size);
        PageDTO<ReviewListDTO> reviews = reviewService.getAllReview(pageable);
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "공연별/전시별 리뷰 조회", description = "/reviews/PF123456")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/reviewList/{objectId}")
    public ResponseEntity<?> getAllReviewByObjectId(
            @PathVariable String objectId,
            @Parameter(description = "표시할 페이지")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "한 페이지에 볼 게시물 수")
            @RequestParam(defaultValue = "8") int size){
        Pageable pageable = PageRequest.of(page-1, size);
        PageDTO<ReviewListDTO> reviews = reviewService.getAllReviewByObjectId(objectId, pageable);
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "상세 리뷰 조회", description = "/reviews/1")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/reviews/{id}")
    public ResponseEntity<?> getReview(@PathVariable Long id){
        Review review = reviewService.getReview(id);
        if(review == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO.Content");
        }
        return ResponseEntity.ok(review);
    }

    @Operation(summary = "리뷰 등록", description = "/reviews")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/reviews")
    public ResponseEntity<?> createReview(HttpServletRequest request, @RequestBody ReviewDTO review){
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");
        String result = "ProcesSuccess";
        if(memberid != null){
            reviewService.createReview(review);
        } else{
            result = "Required.Login";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "리뷰 수정", description = "/reviews/1")
    @ApiResponse(responseCode = "200", description = "성공")
    @PatchMapping("/reviews/{id}")
    public ResponseEntity<?> updateReview(HttpServletRequest request, @PathVariable Long id,@RequestBody ReviewUpdateDto review){
        String result = "ProcessSuccess";

        result = reviewService.updateReview(request, id, review);

        if(result.equals("Required.Login")){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        } else  if(result.equals("NotFound.Review")){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else if(result.equals("Other.User")){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "리뷰 삭제", description = "/reviews/1")
    @ApiResponse(responseCode = "200", description = "성공")
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<?> deleteReview(HttpServletRequest request, @PathVariable Long id){
        String result = "ProcessSuccess";

        result = reviewService.deleteReview(request, id);

        if(result.equals("Required.Login")){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        } else  if(result.equals("NotFound.Review")){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else if(result.equals("Other.User")){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        return ResponseEntity.ok(result);
    }
}
