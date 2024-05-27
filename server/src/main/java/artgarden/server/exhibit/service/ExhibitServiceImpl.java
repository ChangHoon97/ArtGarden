package artgarden.server.exhibit.service;

import artgarden.server.exhibit.entity.Exhibit;
import artgarden.server.exhibit.entity.dto.ExhibitListDTO;
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
    public ExhibitPageDTO getExhibits(String keyword, int days, Pageable pageable) {
        ExhibitPageDTO data = new ExhibitPageDTO();
        Page<Exhibit> exhibits = null;

        LocalDate expectDate = LocalDate.now().plusDays(days);

        exhibits = exhibitRepository.getExhibits(keyword, expectDate, pageable);

        for(Exhibit exhibit : exhibits.getContent()){
            data.getData().add(new ExhibitListDTO(exhibit));
        }
        data.setPageNo(exhibits.getNumber()+1);
        data.setTotalPages(exhibits.getTotalPages());
        data.setPageSize(exhibits.getSize());
        data.setTotalElements(exhibits.getTotalElements());
        data.setHasNext(exhibits.hasNext());

        return data;
    }

}
