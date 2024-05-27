package artgarden.server.review.entity.dto;

import artgarden.server.common.entity.dto.PagingDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReviewPageDTO extends PagingDTO {
    public List<ReviewListDTO> data = new ArrayList<>();
}
