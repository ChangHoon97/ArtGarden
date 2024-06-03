package artgarden.server.scrap.service;

import artgarden.server.scrap.entity.dto.ScrapPageDTO;
import org.springframework.data.domain.Pageable;

public interface ScrapService {
    ScrapPageDTO selectMyScrapList(String memberid, Pageable pageable);

}
