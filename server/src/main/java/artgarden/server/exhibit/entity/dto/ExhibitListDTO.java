package artgarden.server.exhibit.entity.dto;

import artgarden.server.exhibit.entity.Exhibit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitListDTO {

    @Schema(description = "전시 ID", example = "123456")
    private String id;
    @Schema(description = "전시회 제목", example = "봄봄봄")
    private String name;
    @Schema(description = "전시회 시작날짜", example ="2024-05-25")
    private LocalDate startdate;
    @Schema(description = "전시회 종료날짜", example = "2025-09-26")
    private LocalDate enddate;
    @Schema(description = "전시 종류", example = "미술")
    private String genre;
    @Schema(description = "지역", example = "서울")
    private String area;
    @Schema(description = "전시 장소", example = "우리집")
    private String place;
    @Schema(description = "전시상태", example = "EXSTATUS01")
    private String exstatus;
    @Schema(description = "전시회 포스터", example = "http://www.culture.go.kr/upload/rdf/24/04/show_2024040414121348608.jpg")
    private String posterurl;

    public ExhibitListDTO(Exhibit exhibit){
        this.setId(exhibit.getId());
        this.setName(exhibit.getName());
        this.setStartdate(exhibit.getStartdate());
        this.setEnddate(exhibit.getEnddate());
        this.setGenre(exhibit.getGenre());
        this.setArea(exhibit.getArea());
        this.setPlace(exhibit.getPlace());
        this.setExstatus(exhibit.getExstatus());
        this.setPosterurl(exhibit.getPosterurl());
    }

}
