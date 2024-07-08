package artgarden.server.common.service;

import artgarden.server.common.entity.Code;
import artgarden.server.common.entity.dto.CodeDTO;
import artgarden.server.common.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{

    private final CodeRepository codeRepository;
    @Override
    public CodeDTO getCodeList(String cdtype, String uppcd, Integer cddepth) {
        CodeDTO vo = new CodeDTO();
        vo.setCodeList(codeRepository.getCodeList(cdtype, uppcd, cddepth));

        return vo;
    }
}
