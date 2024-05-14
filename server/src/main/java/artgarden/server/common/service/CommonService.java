package artgarden.server.common.service;

import artgarden.server.common.entity.Code;
import artgarden.server.common.entity.dto.CodeDTO;

public interface CommonService {
    CodeDTO getCodeList(String cdtype, String uppcd, Integer cddepth);
}
