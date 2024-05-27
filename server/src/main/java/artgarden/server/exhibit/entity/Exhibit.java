package artgarden.server.exhibit.entity;

import artgarden.server.exhibit.entity.dto.ExhibitApiDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Exhibit {

    @Schema(description = "전시회 ID", example = "123456")
    @Id
    private String id;
    @Schema(description = "전시회 제목", example = "봄봄봄")
    private String name;
    @Schema(description = "전시회 시작날짜", example ="2024-05-25")
    private LocalDate startdate;
    @Schema(description = "전시회 종료날짜", example = "2025-09-26")
    private LocalDate enddate;
    @Schema(description = "전시 종류", example = "미술")
    private String genre;
    @Schema(description = "전시 종류코드", example = "EG01")
    private String genrecd;
    @Schema(description = "지역", example = "서울")
    private String area;
    @Schema(description = "지역코드", example = "AREA01")
    private String areacd;
    @Schema(description = "전시 장소", example = "우리집")
    private String place;
    @Schema(description = "전시상태", example = "EXSTATUS01")
    private String exstatus;
    @Schema(description = "전시회 포스터", example = "http://www.culture.go.kr/upload/rdf/24/04/show_2024040414121348608.jpg")
    private String posterurl;
    @Schema(description = "전시회 게시글 조회수", example = "127")
    private int visitcnt;
    @Schema(description = "전시회 스크랩 수", example = "13")
    private int scrapcnt;
    @Schema(description = "작성자", example = "CHANGHOON")
    private String regid;
    @Schema(description = "작성일", example = "2024-04-01 23:48:30")
    private LocalDateTime regdt;
    @Schema(description = "수정자", example = "CHANGHOON")
    private String updid;
    @Schema(description = "수정일", example = "2024-04-01 23:48:30")
    private LocalDateTime upddt;

    public void updateFromApiDto(ExhibitApiDTO dto){
        this.id = dto.getId();
        this.name = dto.getName();
        this.startdate = dto.getStartdate();
        this.enddate = dto.getEnddate();
        this.genre = dto.getGenre();
        this.area = dto.getArea();
        this.place = dto.getPlace();
        this.posterurl = dto.getPosterurl();
        this.regdt = dto.getRegdt();
        this.regid = dto.getRegid();
    }
}
