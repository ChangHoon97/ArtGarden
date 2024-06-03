package artgarden.server.scrap.entity.dto;

import artgarden.server.common.entity.dto.PagingDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ScrapPageDTO extends PagingDTO {
    List<ScrapMyDTO> myDTOList = new ArrayList<>();
}
