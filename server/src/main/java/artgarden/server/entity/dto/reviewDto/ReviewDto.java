package artgarden.server.entity.dto.reviewDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDto {

    @Schema(description = "공연 ID", example = "1")
    private String perform_id;
    @Schema(description = "리뷰 내용", example = "너무 좋았습니다. 최고!")
    private String content;
    @Schema(description = "리뷰 별점", example = "2.5")
    private Double rate;
    @Schema(description = "회원 ID", example = "1")
    private Long member_id;
    @Schema(description = "작성 날짜", example = "2023-11-29 08:35:26.182549")
    private LocalDateTime created_at;
    @Schema(description = "수정 날짜", example = "2023-11-29 18:32:58.187159")
    private LocalDateTime modified_at;
}
