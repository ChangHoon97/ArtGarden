package artgarden.server.entity.dto.performanceDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private String age;
    private String price;
    private String casting;
    private String production;
    private String genre;
    private String performStatus;
    private String posterUrl;
    private String openRun;
    private String area;
}
