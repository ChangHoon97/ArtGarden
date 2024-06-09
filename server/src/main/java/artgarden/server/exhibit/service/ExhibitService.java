package artgarden.server.exhibit.service;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.exhibit.entity.Exhibit;
import artgarden.server.exhibit.entity.dto.ExhibitDetailDTO;
import artgarden.server.exhibit.entity.dto.ExhibitPageDTO;
import org.springframework.data.domain.Pageable;

public interface ExhibitService {
    public PageDTO<ExhibitDetailDTO> getExhibits(String keyword, int days, Pageable pageable, String[] searchAreaArr, String orderby, String memberid);

    public ExhibitDetailDTO selectExhibit(String id, String memberid);

    public String updateScrapCnt(String id, String status);

}
