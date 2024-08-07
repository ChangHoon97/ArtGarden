package artgarden.server.scrap.service;

import artgarden.server.common.entity.dto.PageDTO;
import artgarden.server.exhibit.service.ExhibitService;
import artgarden.server.performance.service.PerformanceService;
import artgarden.server.scrap.entity.Scrap;
import artgarden.server.scrap.entity.dto.ScrapDTO;
import artgarden.server.scrap.entity.dto.ScrapMyDTO;
import artgarden.server.scrap.entity.dto.ScrapingDTO;
import artgarden.server.scrap.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService{

    private final ScrapRepository scrapRepository;

    private final PerformanceService performanceService;

    private final ExhibitService exhibitService;

    @Override
    public PageDTO<ScrapMyDTO> selectMyScrapList(String memberid, Pageable pageable) {
        Page<ScrapMyDTO> scraps = scrapRepository.findAllByMemberid(memberid, pageable);

        PageDTO<ScrapMyDTO> dto = new PageDTO<>(scraps);

        return dto;
    }

    @Override
    @Transactional
    public String updateScraping(String memberid, ScrapingDTO dto) {
        String objectid = dto.getObjectid();
        Scrap chkscrap = scrapRepository.findByMemberidAndObjectid(memberid, objectid);
        ScrapDTO to = new ScrapDTO();
        String result = "";
        String updateStatus = "ASC";

        if(chkscrap != null){   //기존 스크랩 존재하는 경우 업데이트
            System.out.println("기존 스크랩 존재한다");
            scrapRepository.updateScrapYN(memberid, objectid, !chkscrap.isScrapyn());
            if(chkscrap.isScrapyn()){   //기존 true ====> false
                updateStatus = "DESC";
            }
        } else{ //없을 경우 scrapyn = true로 저장
            System.out.println("기존 스크랩 없다.");
            to.setScrapyn(true);
            to.setMemberid(memberid);
            to.setObjectid(objectid);
            to.setRegid(memberid);
            to.setRegdt(LocalDateTime.now());
            Scrap scrap = new Scrap();
            scrap.ScrapDTOToEntity(to);
            scrapRepository.save(scrap);
        }
        result = updateScrapcnt(objectid, updateStatus);

        return result;
    }

    //공연,전시 scrapcnt 수정
    private String updateScrapcnt(String objectid, String updateStatus){
        String result = "";
        String objecttype = "";
        if(objectid != null && objectid.length() > 2){
            if(objectid.startsWith("PF")){
                result = performanceService.updateScrapCnt(objectid, updateStatus);
            } else if (objectid.startsWith("EX")){
                result = exhibitService.updateScrapCnt(objectid, updateStatus);
            } else{
                result = "ProcessFail";
            }
        }
        return result;
    }


    public String selectScrapByObjectid(String memberid, String objectid){
        String result = "false";
        Scrap scrap = scrapRepository.findByMemberidAndObjectid(memberid, objectid);

        if(scrap != null && scrap.isScrapyn()){
            result = "true";
        }

        return result;
    }

}
