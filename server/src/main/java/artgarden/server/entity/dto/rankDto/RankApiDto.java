package artgarden.server.entity.dto.rankDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankApiDto {
    private LocalDate rankDate;
    private List<String> performId;
}
