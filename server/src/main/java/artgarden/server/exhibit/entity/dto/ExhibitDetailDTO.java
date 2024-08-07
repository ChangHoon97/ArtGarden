package artgarden.server.exhibit.entity.dto;

import artgarden.server.exhibit.entity.Exhibit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitDetailDTO {

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
    private String status;
    @Schema(description = "전시회 포스터", example = "http://www.culture.go.kr/upload/rdf/24/04/show_2024040414121348608.jpg")
    private String posterurl;
    @Schema(description = "전시회 게시글 조회수", example = "127")
    private int visitcnt;
    @Schema(description = "전시회 스크랩 수", example = "13")
    private int scrapcnt;
    @Schema(description = "회원의 찜여부", example ="true")
    private boolean scrapyn;

    public ExhibitDetailDTO(Exhibit exhibit){
        this.setId(exhibit.getId());
        this.setName(exhibit.getName());
        this.setStartdate(exhibit.getStartdate());
        this.setEnddate(exhibit.getEnddate());
        this.setGenre(exhibit.getGenre());
        this.setArea(exhibit.getArea());
        this.setPlace(exhibit.getPlace());
        this.setStatus(exhibit.getStatus());
        this.setPosterurl(exhibit.getPosterurl());
        this.setVisitcnt(exhibit.getVisitcnt());
        this.setScrapcnt(exhibit.getScrapcnt());
    }

    public ExhibitDetailDTO(Exhibit exhibit, boolean scrapyn){
        this.setId(exhibit.getId());
        this.setName(exhibit.getName());
        this.setStartdate(exhibit.getStartdate());
        this.setEnddate(exhibit.getEnddate());
        this.setGenre(exhibit.getGenre());
        this.setArea(exhibit.getArea());
        this.setPlace(exhibit.getPlace());
        this.setStatus(exhibit.getStatus());
        this.setPosterurl(exhibit.getPosterurl());
        this.setVisitcnt(exhibit.getVisitcnt());
        this.setScrapcnt(exhibit.getScrapcnt());
        this.setScrapyn(scrapyn);
    }

}
