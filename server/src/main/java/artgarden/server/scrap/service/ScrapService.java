package artgarden.server.scrap.service;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.scrap.entity.dto.ScrapMyDTO;
import artgarden.server.scrap.entity.dto.ScrapingDTO;
import org.springframework.data.domain.Pageable;

public interface ScrapService {
    PageDTO<ScrapMyDTO> selectMyScrapList(String memberid, Pageable pageable);

    String updateScraping(String memberid, ScrapingDTO dto);

    String selectScrapByObjectid(String memberid, String objectid);

}
