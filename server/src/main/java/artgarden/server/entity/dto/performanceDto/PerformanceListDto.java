package artgarden.server.entity.dto.performanceDto;

import artgarden.server.entity.Performance;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
public class PerformanceListDto {

    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private String place;
    private String price;
    private String posterUrl;

    public PerformanceListDto fromEntity(Performance performance){
        PerformanceListDto dto = new PerformanceListDto();
        dto.setId(performance.getId());
        dto.setName(performance.getName());
        dto.setStartDate(dateFormat(performance.getStartDate()));
        dto.setPlace(performance.getPlace());
        dto.setPrice(performance.getPrice());
        dto.setPosterUrl(performance.getPosterUrl());

        if(performance.getOpenRun().equals("Y")){
            dto.setEndDate("오픈런");
        }else{
            dto.setEndDate(dateFormat(performance.getEndDate()));
        }

        return dto;
    }

    private String dateFormat(LocalDate localdate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localdate.format(formatter);
    }
}
