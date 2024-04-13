package artgarden.server.review.entity;

import artgarden.server.review.entity.dto.ReviewDto;
import artgarden.server.review.entity.dto.ReviewUpdateDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewid;
    private String performid;
    @Column(length=4000)
    private String content;
    private Double rate;
    private String memberid;
    private String regid;
    private LocalDateTime regdt;
    private String updid;
    private LocalDateTime upddt;

    public void createFromDto(ReviewDto dto){
        this.performid = dto.getPerformid();
        this.content = dto.getContent();
        this.rate = dto.getRate();
        this.memberid = dto.getMemberid();
        this.regdt = dto.getRegdt();
    }

    public void updateFromDto(ReviewUpdateDto dto){
        this.content = dto.getContent();
        this.rate = dto.getRate();
        this.updid = dto.getUpdid();
        this.upddt = dto.getUpddt();
    }

}
