package artgarden.server.review.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewUpdateDTO {
    @Schema(description = "리뷰 ID", example = "1")
    private Long reviewid;
    @Schema(description = "리뷰 내용", example = "너무 좋았습니다. 최고!")
    private String content;
    @Schema(description = "리뷰 별점", example = "2.5")
    private Double rate;
    
    @Schema(description = "수정자", example = "백엔드에서 처리")
    private String updid;
    @Schema(description = "수정일", example = "백엔드에서 처리")
    private LocalDateTime upddt;
}
