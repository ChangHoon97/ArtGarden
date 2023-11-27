package artgarden.server.entity.dto.reviewDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewUpdateDto {
    private Long id;
    private String content;
    private Long rate;
    private LocalDateTime modified_at;
}
