package artgarden.server.entity;

import artgarden.server.entity.dto.rankDto.RankApiDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate rankDate;
    @ElementCollection
    private List<String> performId;

    public void updateFromApiDto(RankApiDto dto){
        this.rankDate = dto.getRankDate();
        this.performId = dto.getPerformId();
    }
}