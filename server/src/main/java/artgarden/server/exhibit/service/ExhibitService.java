package artgarden.server.exhibit.service;

import artgarden.server.exhibit.entity.Exhibit;
import artgarden.server.exhibit.entity.dto.ExhibitPageDTO;
import org.springframework.data.domain.Pageable;

public interface ExhibitService {
    public ExhibitPageDTO getExhibits(String keyword, int days, Pageable pageable,String[] searchAreaArr, String orderby);

    public Exhibit selectExhibit(String id);

    public String updateScrapCnt(String id, String status);

}
