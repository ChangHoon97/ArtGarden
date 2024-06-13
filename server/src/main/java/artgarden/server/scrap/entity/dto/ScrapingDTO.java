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
}
