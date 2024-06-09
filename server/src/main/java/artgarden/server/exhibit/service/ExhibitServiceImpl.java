package artgarden.server.exhibit.service;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.exhibit.entity.Exhibit;
import artgarden.server.exhibit.entity.dto.ExhibitDetailDTO;
import artgarden.server.exhibit.entity.dto.ExhibitPageDTO;
import artgarden.server.exhibit.repository.ExhibitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ExhibitServiceImpl implements ExhibitService {

    private final ExhibitRepository exhibitRepository;
    @Override
    public PageDTO<ExhibitDetailDTO> getExhibits(String keyword, int days, Pageable pageable, String[] searchAreaArr, String orderby, String memberid) {
        Page<ExhibitDetailDTO> exhibits = null;

        LocalDate expectDate = LocalDate.now().plusDays(days);
        if(orderby.equals("popular")){
            exhibits = exhibitRepository.getExhibitsPopular(keyword, expectDate, searchAreaArr, memberid, pageable);
        } else if(orderby.equals("latest")){
            exhibits = exhibitRepository.getExhibitsLatest(keyword, expectDate, searchAreaArr, memberid,pageable);
        } else if(orderby.equals("scrap")){
            exhibits = exhibitRepository.getExhibitsScrap(keyword, expectDate, searchAreaArr, memberid, pageable);
        }

        PageDTO<ExhibitDetailDTO> dto = new PageDTO(exhibits.getNumber()+1, exhibits.getTotalPages(), exhibits.getSize(), exhibits.getTotalElements(), exhibits.hasNext(), exhibits.getContent());


        return dto;
    }

    public ExhibitDetailDTO selectExhibit(String id, String memberid){

        return exhibitRepository.selectExhibit(id, memberid);
    }

    @Override
    public String updateScrapCnt(String id, String status) {
        String result = "ProcessSuccess";
        if(status.equals("ASC")){
            exhibitRepository.updateScrapCntASC(id);
        } else if(status.equals("DESC")){
            exhibitRepository.updateScrapCntDESC(id);
        } else{
            result = "ProcessFail";
        }
        return result;
    }


}
