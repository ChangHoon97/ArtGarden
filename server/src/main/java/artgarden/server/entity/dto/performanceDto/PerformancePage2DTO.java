package artgarden.server.entity.dto.performanceDto;

import artgarden.server.entity.dto.PagingDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PerformancePage2DTO extends PagingDTO {
    public List<PerformanceListDTO> data = new ArrayList<>();
}
