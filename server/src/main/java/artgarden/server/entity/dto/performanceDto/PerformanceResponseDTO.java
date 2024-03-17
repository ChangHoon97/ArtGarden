package artgarden.server.entity.dto.performanceDto;

import artgarden.server.entity.dto.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PerformanceResponseDTO extends PageDTO {

    @Schema(description = "데이터 리스트", example = "{..., ..., ...}, {..., ..., ...} , ....")
    private List<PerformanceListDTO> list = new ArrayList<>();
}
