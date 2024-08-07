package artgarden.server.review.service;


import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.review.entity.Review;
import artgarden.server.review.entity.dto.ReviewDTO;
import artgarden.server.review.entity.dto.ReviewListDTO;
import artgarden.server.review.entity.dto.ReviewUpdateDto;
import artgarden.server.review.repository.ReviewRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    public PageDTO<ReviewListDTO> getAllReview(Pageable pageable){
        Page<ReviewListDTO> reviews = reviewRepository.findALlPage(pageable);
        PageDTO<ReviewListDTO> dto = new PageDTO<>(reviews);

        return dto;
    }

    public PageDTO<ReviewListDTO> getAllReviewByObjectId(String id, Pageable pageable){

        Page<ReviewListDTO> reviews = reviewRepository.findAllByObjectId(id, pageable);
        PageDTO<ReviewListDTO> dto = new PageDTO<>(reviews);

        return dto;
    }

    public Review getReview(Long id){
        Optional<Review> review = reviewRepository.findById(id);

        return review.orElseThrow();
    }

    @Transactional
    public void createReview(ReviewDTO dto){
        dto.setRegdt(LocalDateTime.now());
        Review review = new Review();
        review.createFromDto(dto);

        reviewRepository.save(review);
    }

    @Transactional
    public String updateReview(HttpServletRequest request, Long id, ReviewUpdateDto dto){
        String result = "ProcessSuccess";
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");
        Optional<Review> chkreview = reviewRepository.findById(dto.getReviewid());
        Review review = null;

        if(memberid == null){
            result = "Required.Login";
        } else if(chkreview.isEmpty()){
            result = "NotFound.Review";
        } else if(!chkreview.get().getMemberid().equals(memberid)){
            result = "Other.User";
        } else{
            dto.setUpddt(LocalDateTime.now());
            review = reviewRepository.findById(id).orElseThrow();
            review.updateFromDto(dto);
            reviewRepository.save(review);
        }

        return result;
    }

    @Transactional
    public String deleteReview(HttpServletRequest request, Long id){
        String result = "ProcessSuccess";
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");

        Optional<Review> chkreview = reviewRepository.findById(id);

        if(memberid == null){
            result = "Required.Login";
        } else if(chkreview.isEmpty()){
            result = "NotFound.Review";
        } else if(!chkreview.get().getMemberid().equals(memberid)){
            result = "Other.User";
        } else{
            reviewRepository.deleteById(id);
        }

        return result;
    }
}
