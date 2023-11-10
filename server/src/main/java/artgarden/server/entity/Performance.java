package artgarden.server.entity;

import artgarden.server.entity.dto.performanceDto.PerformanceApiDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class Performance {

    @Id
    private String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String place;
    @Column(length = 1024)
    private String time;
    private String age;
    private String price;
    private String casting;
    private String production;
    private String genre;
    private String perform_status;
    private String posterUrl;

    public void updateFromApiDto(PerformanceApiDto dto){
        this.id = dto.getId();
        this.name = dto.getName();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.place = dto.getPlace();
        this.time = dto.getTime();
        this.age = dto.getAge();
        this.price = dto.getPrice();
        this.casting = dto.getCasting();
        this.production = dto.getProduction();
        this.genre = dto.getGenre();
        this.perform_status=dto.getPerform_status();
        this.posterUrl=dto.getPosterUrl();
    }



}
