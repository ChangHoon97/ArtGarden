package artgarden.server.scrap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapid;
    private String objectid;
    private String memberid;
    private boolean scrapyn;
    private String regid;
    private LocalDateTime regdt;
    private String updid;
    private LocalDateTime upddt;
}
