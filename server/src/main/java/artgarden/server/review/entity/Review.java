package artgarden.server.review.entity;

import artgarden.server.review.entity.dto.ReviewDTO;
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
    private String objectid;
    @Column(length=4000)
    private String content;
    private Double rate;
    private String memberid;
    private String regid;
    private LocalDateTime regdt;
    private String updid;
    private LocalDateTime upddt;

    public void createFromDto(ReviewDTO dto){
        this.objectid = dto.getObjectid();
        this.content = dto.getContent();
        this.rate = dto.getRate();
        this.memberid = dto.getMemberid();
        this.regdt = dto.getRegdt();
    }

    public void updateFromDto(ReviewUpdateDto dto){
        this.content = dto.getContent();
        this.rate = dto.getRate();
        this.updid = dto.getUpdid();
        this.upddt = LocalDateTime.now();
    }

}
