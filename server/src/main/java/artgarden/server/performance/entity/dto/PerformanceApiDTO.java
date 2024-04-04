package artgarden.server.performance.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceApiDTO {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String place;
    private String time;
    private Double age;
    private String price;
    private String casting;
    private String production;
    private String genre;
    private String genrecd;
    private String performStatus;
    private String posterUrl;
    private String openRun;
    private String area;
    private String areacd;
    private String regid;
    private LocalDateTime regdt;
}
