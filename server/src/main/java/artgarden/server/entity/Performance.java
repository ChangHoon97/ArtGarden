package artgarden.server.entity;

import artgarden.server.entity.dto.performanceDto.PerformanceApiDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Performance {

    @Schema(description = "공연 ID", example = "PF216230")
    @Id
    private String id;
    @Schema(description = "공연 제목", example = "왓 이프")
    private String name;
    @Schema(description = "공연 시작날짜", example = "2023-05-19")
    private LocalDate startDate;
    @Schema(description = "공연 종료날짜", example = "2023-12-31")
    private LocalDate endDate;
    @Schema(description = "공연 장소", example = "룸씨어터 (룸씨어터)")
    private String place;
    @Schema(description = "공연 시간", example = "수요일 ~ 금요일(19:30), 토요일(14:00,17:00,17:30), 일요일(13:00,15:00,16:00), HOL(15:00,16:00)")
    @Column(length = 1024)
    private String time;
    @Schema(description = "제한 연령", example = "만 13세 이상")
    private String age;
    @Schema(description = "공연 가격", example = "전석 50,000원")
    private String price;
    @Schema(description = "출연진", example = "이다은, 이서정, 정세지, 김하준, 최우성, 노혜성, 표헤미 등")
    private String casting;
    @Schema(description = "제작사", example = "이창훈 프로덕션")
    private String production;
    @Schema(description = "공연 장르", example = "뮤지컬")
    private String genre;
    @Schema(description = "공연 장르코드", example = "GENRE01")
    private String genrecd;
    @Schema(description = "공연 상태", example = "공연중")
    private String performStatus;
    @Schema(description = "공연 포스터 url", example = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF216230_230405_125449.gif")
    private String posterUrl;
    @Schema(description = "오픈런", example = "N")
    private String openRun;
    @Schema(description = "지역", example = "서울특별시")
    private String area;
    @Schema(description = "지역코드", example = "AREA01")
    private String areacd;
    @Schema(description = "공연 게시글 조회수", example = "127")
    private int visitCnt;
    @Schema(description = "공연 스크랩 수", example = "13")
    private int scrapCnt;
    @Schema(description = "작성자", example = "CHANGHOON")
    private String regid;
    @Schema(description = "작성일", example = "2024-04-01 23:48:30")
    private LocalDateTime regdt;
    @Schema(description = "수정자", example = "CHANGHOON")
    private String updid;
    @Schema(description = "수정일", example = "2024-04-01 23:48:30")
    private LocalDateTime upddt;

    public void updateFromApiDto(PerformanceApiDTO dto){
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
        this.genrecd = dto.getGenrecd();
        this.performStatus = dto.getPerformStatus();
        this.posterUrl = dto.getPosterUrl();
        this.openRun = dto.getOpenRun();
        this.area = dto.getArea();
        this.areacd = dto.getAreacd();
    }



}
