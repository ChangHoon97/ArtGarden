package artgarden.server.performance.entity.dto;

import artgarden.server.common.entity.dto.PagingDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PerformancePageDTO extends PagingDTO {
    public List<PerformanceListDTO> data = new ArrayList<>();
}
