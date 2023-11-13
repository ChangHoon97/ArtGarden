package artgarden.server.entity.dto.performanceDto;

import artgarden.server.entity.Performance;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class PerformanceDetailDto {

    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private String place;
    private String time;
    private String age;
    private String price;
    private String casting;
    private String production;
    private String genre;
    private String performStatus;
    private String posterUrl;

    public void fromEntity(Performance performance){
        this.setId(performance.getId());
        this.setName(performance.getName());
        this.setStartDate(dateFormat(performance.getStartDate()));
        this.setPlace(performance.getPlace());
        this.setTime(performance.getTime());
        this.setAge(performance.getAge());
        this.setPrice(performance.getPrice());
        this.setCasting(performance.getCasting());
        this.setProduction(performance.getProduction());
        this.setGenre(performance.getGenre());
        this.setPerformStatus(performance.getPerformStatus());
        this.setPosterUrl(performance.getPosterUrl());

        if(performance.getOpenRun().equals("Y")){
            this.setEndDate("오픈런");
        }else{
            this.setEndDate(dateFormat(performance.getEndDate()));
        }
    }

    private String dateFormat(LocalDate localdate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localdate.format(formatter);
    }
}
