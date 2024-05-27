package artgarden.server.exhibit.entity.dto;

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
public class ExhibitApiDTO {

    private String id;
    private String name;
    private LocalDate startdate;
    private LocalDate enddate;
    private String genre;
    private String area;
    private String place;
    private String posterurl;
    private LocalDateTime regdt;
    private String regid;
}
