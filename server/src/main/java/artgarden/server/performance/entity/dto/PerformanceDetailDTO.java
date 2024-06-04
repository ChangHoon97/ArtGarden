package artgarden.server.performance.entity.dto;

import artgarden.server.performance.entity.Performance;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Schema(description = "공연 상세 정보 DTO")
public class PerformanceDetailDTO {

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
    @Schema(description = "공연 시간", example = "수요일 ~ 금요일(19:30), 토요일(14:00,17:00,17:30), 일요일(13:00,15:00,16:00), HOL(15:00,16:00)")
    private String time;
    @Schema(description = "제한 연령", example = "만 13세 이상")
    private Double age;
    @Schema(description = "공연 가격", example = "전석 50,000원")
    private String price;
    @Schema(description = "출연진", example = "이다은, 이서정, 정세지, 김하준, 최우성, 노혜성, 표헤미 등")
    private String casting;
    @Schema(description = "제작사", example = "이창훈 프로덕션")
    private String production;
    @Schema(description = "공연 장르", example = "뮤지컬")
    private String genre;
    @Schema(description = "공연 상태", example = "공연중")
    private String status;
    @Schema(description = "공연 포스터 url", example = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF216230_230405_125449.gif")
    private String posterUrl;
    @Schema(description = "공연 게시글 조회수", example = "127")
    private int visitcnt;
    @Schema(description = "공연 스크랩 수", example = "13")
    private int scrapcnt;

    public void fromEntity(Performance performance){
        this.setId(performance.getId());
        this.setName(performance.getName());
        this.setStartdate(dateFormat(performance.getStartdate()));
        this.setPlace(performance.getPlace());
        this.setTime(performance.getTime());
        this.setAge(performance.getAge());
        this.setPrice(performance.getPrice());
        this.setCasting(performance.getCasting());
        this.setProduction(performance.getProduction());
        this.setGenre(performance.getGenre());
        this.setStatus(performance.getPerformstatus());
        this.setPosterUrl(performance.getPosterurl());
        this.setVisitcnt(performance.getVisitcnt());
        this.setScrapcnt(performance.getScrapcnt());

        if(performance.getOpenrun().equals("Y")){
            this.setEnddate("오픈런");
        }else{
            this.setEnddate(dateFormat(performance.getEnddate()));
        }
    }

    private String dateFormat(LocalDate localdate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localdate.format(formatter);
    }
}
