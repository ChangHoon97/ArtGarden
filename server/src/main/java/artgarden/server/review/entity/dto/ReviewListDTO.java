package artgarden.server.review.entity.dto;

import artgarden.server.review.entity.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewListDTO {

    @Schema(description = "리뷰 ID", example = "1")
    private Long reviewid;

    @Schema(description = "공연/전시/팝업 ID", example = "PF123456")
    private String objectid;

    @Schema(description = "내용", example = "너무 좋았어요")
    private String content;

    @Schema(description = "평점", example = "3")
    private double rate;

    @Schema(description = "회원 ID", example = "changhoon")
    private String memberid;

    @Schema(description = "작성자")
    private String regid;

    @Schema(description = "작성일")
    private LocalDateTime regdt;

    @Schema(description = "수정자")
    private String updid;

    @Schema(description = "수정일")
    private LocalDateTime upddt;

    @Schema(description = "썸네일 주소")
    private String posterurl;

    @Schema(description = "제목")
    private String name;

    @Schema(description = "장르")
    private String genre;

    @Schema(description = "별명")
    private String nickname;

    public ReviewListDTO(Review review, String posterurl, String name, String genre, String nickname){
        this.reviewid = review.getReviewid();
        this.objectid = review.getObjectid();
        this.content = review.getContent();
        this.rate = review.getRate();
        this.memberid = review.getMemberid();
        this.regid = review.getRegid();
        this.regdt = review.getRegdt();
        this.updid = review.getUpdid();
        this.upddt = review.getUpddt();
        this.posterurl = posterurl;
        this.name = name;
        this.genre = genre;
        this.nickname = nickname;
    }

}
