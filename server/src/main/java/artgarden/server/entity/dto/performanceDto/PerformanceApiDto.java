package artgarden.server.entity.dto.performanceDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceApiDto {
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
}
