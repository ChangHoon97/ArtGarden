package artgarden.server.common.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingDTO {

    @Schema(description = "현재 페이지 번호", example = "1")
    public int pageNo;
    @Schema(description = "현재 페이지의 데이터 수", example = "30")
    public int pageSize;
    @Schema(description = "총 페이지 수", example = "7")
    public int totalPages;
    @Schema(description = "총 데이터 수", example = "192")
    public Long totalElements;
    @Schema(description = "다음 페이지 존재여부", example = "true")
    public boolean hasNext;
    
}
