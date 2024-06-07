package artgarden.server.rank.entity.dto;

import artgarden.server.performance.entity.Performance;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class RankListDto {

    @Schema(description = "공연 ID", example = "PF216230")
    private String id;
    @Schema(description = "공연 제목", example = "왓 이프")
    private String name;
    @Schema(description = "공연 시작날짜", example = "2023-05-19")
    private String startdate;
    @Schema(description = "공연 종료날짜", example = "2023-12-31")
    private String enddate;
    @Schema(description = "공연 장소", example = "룸씨어터 (룸씨어터)")
    private String place;
    @Schema(description = "공연 가격", example = "전석 50,000원")
    private String price;
    @Schema(description = "공연 포스터 url", example = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF216230_230405_125449.gif")
    private String posterurl;
    @Schema(description = "공연 장르", example = "뮤지컬")
    private String genre;
    @Schema(description = "공연 상태", example = "공연중")
    private String status;
    @Schema(description = "공연 게시글 조회수", example = "127")
    private int visitcnt;
    @Schema(description = "공연 스크랩 수", example = "13")
    private int scrapcnt;
    @Schema(description = "지역", example ="대구")
    private String area;
    @Schema(description = "순위", example = "1")
    private int rankNum;

    public RankListDto(Performance performance, int rankNumber){
        this.id = performance.getId();
        this.name = performance.getName();
        this.startdate = dateFormat(performance.getStartdate());
        this.place = performance.getPlace();
        this.price = performance.getPrice();
        this.posterurl = performance.getPosterurl();
        this.rankNum = rankNumber;
        this.genre = performance.getGenre();
        this.status = performance.getPerformstatus();
        this.visitcnt = performance.getVisitcnt();
        this.scrapcnt = performance.getScrapcnt();
        this.area = performance.getArea();

        if(performance.getOpenrun().equals("Y")){
            this.enddate = "오픈런";
        }else{
            this.enddate = dateFormat(performance.getEnddate());
        }
    }

    private String dateFormat(LocalDate localdate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localdate.format(formatter);
    }
}
