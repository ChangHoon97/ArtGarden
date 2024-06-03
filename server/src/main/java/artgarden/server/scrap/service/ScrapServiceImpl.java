package artgarden.server.scrap.service;

import artgarden.server.scrap.entity.Scrap;
import artgarden.server.scrap.entity.dto.ScrapMyDTO;
import artgarden.server.scrap.entity.dto.ScrapPageDTO;
import artgarden.server.scrap.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService{
    private final ScrapRepository scrapRepository;

    @Override
    public ScrapPageDTO selectMyScrapList(String memberid, Pageable pageable) {
        ScrapPageDTO data = new ScrapPageDTO();
        Page<Scrap> scraps = scrapRepository.findAllByMemberid(memberid, pageable);

        for(Scrap scrap : scraps.getContent()){
            data.getMyDTOList().add(new ScrapMyDTO(scrap));
        }

        data.setPageNo(scraps.getNumber()+1);
        data.setTotalPages(scraps.getTotalPages());
        data.setPageSize(scraps.getSize());
        data.setTotalElements(scraps.getTotalElements());
        data.setHasNext(scraps.hasNext());

        return data;
    }
}
