package artgarden.server.entity.dto.performanceDto;

import artgarden.server.entity.Performance;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PerformanceListDto {

    private String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String place;
    private String price;
    private String posterUrl;

    public PerformanceListDto fromEntity(Performance performance){
        PerformanceListDto dto = new PerformanceListDto();
        dto.setId(performance.getId());
        dto.setName(performance.getName());
        dto.setStartDate(performance.getStartDate());
        dto.setEndDate(performance.getEndDate());
        dto.setPlace(performance.getPlace());
        dto.setPrice(performance.getPrice());
        dto.setPosterUrl(performance.getPosterUrl());

        return dto;
    }
}
