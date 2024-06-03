package artgarden.server.scrap.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScrapingDTO {

    @Schema(description = "공연/전시 ID", example = "EX123456")
    private String objectid;
    @Schema(description = "스크랩Y/N", example = "이 값은 안넣어도 됩니다")
    private boolean scrapyn;
    @Schema(description = "작성자", example = "이창훈")
    private String regid;
    @Schema(description = "작성일", example = "2023-11-29 08:35:26.182549")
    private LocalDateTime regdt;
    @Schema(description = "수정자", example = "이창훈")
    private String updid;
    @Schema(description = "수정일", example = "2023-11-29 18:32:58.187159")
    private LocalDateTime upddt;
}
