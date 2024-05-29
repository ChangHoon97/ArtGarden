package artgarden.server.member.service;

import artgarden.server.member.entity.dto.MemberJoinDTO;

public interface MemberService {
    public String insertMember(MemberJoinDTO dto);
}
