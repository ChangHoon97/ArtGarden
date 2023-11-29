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
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public void createFromDto(ReviewDto dto){
        this.perform_id = dto.getPerform_id();
        this.content = dto.getContent();
        this.rate = dto.getRate();
        this.member_id = dto.getMember_id();
        this.created_at = dto.getCreated_at();
    }

    public void updateFromDto(ReviewUpdateDto dto){
        this.content = dto.getContent();
        this.rate = dto.getRate();
        this.modified_at = dto.getModified_at();
    }

}
