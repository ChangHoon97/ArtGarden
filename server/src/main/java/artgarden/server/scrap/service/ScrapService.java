package artgarden.server.scrap.service;

import artgarden.server.scrap.entity.dto.ScrapPageDTO;
import artgarden.server.scrap.entity.dto.ScrapingDTO;
import org.springframework.data.domain.Pageable;

public interface ScrapService {
    ScrapPageDTO selectMyScrapList(String memberid, Pageable pageable);

    String updateScraping(String memberid, ScrapingDTO dto);

}
