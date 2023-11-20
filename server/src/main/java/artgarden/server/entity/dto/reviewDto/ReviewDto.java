package artgarden.server.entity.dto.reviewDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDto {

    private Long id;
    private String perform_id;
    private String content;
    private Long rate;
    private Long member_id;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
}
