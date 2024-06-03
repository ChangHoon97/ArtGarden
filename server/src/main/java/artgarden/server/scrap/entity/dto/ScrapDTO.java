package artgarden.server.scrap.entity.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScrapDTO {
    private Long scrapid;
    private String objectid;
    private String memberid;
    private boolean scrapyn;
    private String regid;
    private LocalDateTime regdt;
    private String updid;
    private LocalDateTime upddt;
}
