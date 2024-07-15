package artgarden.server.review.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewCreateDTO {

    @Schema(description = "공연/전시 ID", example = "1")
    private String objectid;
    @Schema(description = "리뷰 내용", example = "너무 좋았습니다. 최고!")
    private String content;
    @Schema(description = "리뷰 별점", example = "2.5")
    private Double rate;
    @Schema(description = "회원 ID", example = "hyo04044")
    private String memberid;
    
    @Schema(description = "작성자", example = "백엔드에서 처리")
    private String regid;
    @Schema(description = "작성일", example = "백엔드에서 처리")
    private LocalDateTime regdt;
}
