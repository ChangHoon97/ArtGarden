package artgarden.server.common.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageDTO<T> {
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
    @Schema(description = "데이터리스트", example = "")
    public List<T> datalist;

    public PageDTO(Page<T> vo){
        this.pageNo = vo.getNumber()+1;
        this.pageSize = vo.getSize();
        this.totalPages = vo.getTotalPages();
        this.totalElements = vo.getTotalElements();
        this.hasNext = vo.hasNext();
        this.datalist = vo.getContent();
    }
}
