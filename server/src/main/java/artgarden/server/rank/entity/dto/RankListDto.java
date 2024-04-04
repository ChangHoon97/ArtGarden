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
    private String startDate;
    @Schema(description = "공연 종료날짜", example = "2023-12-31")
    private String endDate;
    @Schema(description = "공연 장소", example = "룸씨어터 (룸씨어터)")
    private String place;
    @Schema(description = "공연 가격", example = "전석 50,000원")
    private String price;
    @Schema(description = "공연 포스터 url", example = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF216230_230405_125449.gif")
    private String posterUrl;
    @Schema(description = "순위", example = "1")
    private int rankNum;

    public RankListDto(Performance performance, int rankNumber){
        this.id = performance.getId();
        this.name = performance.getName();
        this.startDate = dateFormat(performance.getStartDate());
        this.place = performance.getPlace();
        this.price = performance.getPrice();
        this.posterUrl = performance.getPosterUrl();
        this.rankNum = rankNumber;

        if(performance.getOpenRun().equals("Y")){
            this.endDate = "오픈런";
        }else{
            this.endDate = dateFormat(performance.getEndDate());
        }
    }

    private String dateFormat(LocalDate localdate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localdate.format(formatter);
    }
}
