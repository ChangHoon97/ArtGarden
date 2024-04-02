package artgarden.server.entity;

import artgarden.server.entity.dto.reviewDto.ReviewDto;
import artgarden.server.entity.dto.reviewDto.ReviewUpdateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String perform_id;
    private String content;
    private Double rate;
    private Long member_id;
    private String regid;
    private LocalDateTime regdt;
    private String updid;
    private LocalDateTime upddt;

    public void createFromDto(ReviewDto dto){
        this.perform_id = dto.getPerform_id();
        this.content = dto.getContent();
        this.rate = dto.getRate();
        this.member_id = dto.getMember_id();
        this.regdt = dto.getRegdt();
    }

    public void updateFromDto(ReviewUpdateDto dto){
        this.content = dto.getContent();
        this.rate = dto.getRate();
        this.updid = dto.getUpdid();
        this.upddt = dto.getUpddt();
    }

}
