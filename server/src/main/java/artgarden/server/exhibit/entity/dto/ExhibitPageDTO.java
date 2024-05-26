package artgarden.server.exhibit.entity.dto;

import artgarden.server.common.entity.dto.PagingDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExhibitPageDTO extends PagingDTO {
    public List<ExhibitListDTO> data = new ArrayList<>();
}
