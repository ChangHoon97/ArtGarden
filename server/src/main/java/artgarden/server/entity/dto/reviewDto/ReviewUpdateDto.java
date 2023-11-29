package artgarden.server.entity.dto.reviewDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewUpdateDto {
    private String content;
    private Double rate;
    private LocalDateTime modified_at;
}
