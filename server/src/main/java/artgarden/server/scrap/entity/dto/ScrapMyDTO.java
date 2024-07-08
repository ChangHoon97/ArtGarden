package artgarden.server.scrap.entity.dto;

import artgarden.server.scrap.entity.Scrap;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScrapMyDTO {

    @Schema(description = "스크랩 id", example = "1")
    private Long scrapid;
    @Schema(description = "공연/전시 id", example = "EX123456")
    private String objectid;
    @Schema(description = "회원 id", example = "hyo04044")
    private String memberid;
    @Schema(description = "스크랩여부", example = "true")
    private boolean scrapyn;
    @Schema(description = "작성자", example = "이창훈")
    private String regid;
    @Schema(description = "작성일", example = "2023-11-29 08:35:26.182549")
    private LocalDateTime regdt;
    @Schema(description = "수정자", example = "이창훈")
    private String updid;
    @Schema(description = "수정일", example = "2023-11-29 18:32:58.187159")
    private LocalDateTime upddt;

    public ScrapMyDTO(Scrap vo){
        this.scrapid = vo.getScrapid();
        this.objectid = vo.getObjectid();
        this.memberid = vo.getMemberid();
        this.scrapyn = vo.isScrapyn();
        this.regid = vo.getRegid();
        this.regdt = vo.getRegdt();
        this.updid = vo.getUpdid();
        this.upddt = vo.getUpddt();
    }
}
