package artgarden.server.entity.dto.performanceDto;

import artgarden.server.entity.Performance;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PerformanceDetailDto {

    private String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String place;
    private String time;
    private String age;
    private String price;
    private String casting;
    private String production;
    private String genre;
    private String perform_status;
    private String posterUrl;

    public void fromEntity(Performance performance){
        this.setId(performance.getId());
        this.setName(performance.getName());
        this.setStartDate(performance.getStartDate());
        this.setEndDate(performance.getEndDate());
        this.setPlace(performance.getPlace());
        this.setTime(performance.getTime());
        this.setAge(performance.getAge());
        this.setPrice(performance.getPrice());
        this.setCasting(performance.getCasting());
        this.setProduction(performance.getProduction());
        this.setGenre(performance.getGenre());
        this.setPerform_status(performance.getPerform_status());
        this.setPosterUrl(performance.getPosterUrl());

    }
}
