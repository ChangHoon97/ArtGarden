package artgarden.server.entity.dto.performanceDto;

import artgarden.server.entity.dto.PagingDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PerformancePageDTO {

    public List<PerformancePage2DTO> pages = new ArrayList<>();
}
