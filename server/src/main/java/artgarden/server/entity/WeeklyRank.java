package artgarden.server.entity;

import artgarden.server.entity.dto.rankDto.RankApiDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class WeeklyRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private LocalDate rankDate;
    @ElementCollection
    private List<String> performId;

    public void updateFromApiDto(RankApiDto dto){
        this.rankDate = dto.getRankDate();
        this.performId = dto.getPerformId();
    }

    public void updateFromRank(WeeklyRank weeklyRank){
        this.rankDate = weeklyRank.getRankDate();
        this.performId = weeklyRank.getPerformId();
    }
}