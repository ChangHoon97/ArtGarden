package artgarden.server.scrap.entity;

import artgarden.server.scrap.entity.dto.ScrapDTO;
import artgarden.server.scrap.entity.dto.ScrapingDTO;
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


    public void ScrapDTOToEntity(ScrapDTO dto){
        this.scrapid = dto.getScrapid();
        this.objectid = dto.getObjectid();
        this.memberid = dto.getMemberid();
        this.scrapyn = dto.isScrapyn();
        this.regid = dto.getRegid();
        this.regdt = dto.getRegdt();
        this.updid = dto.getUpdid();
        this.upddt = dto.getUpddt();
    }
}
