package artgarden.server.member.service;

import artgarden.server.member.entity.dto.MemberJoinDTO;
import artgarden.server.member.entity.dto.OauthLoginDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {
    public String insertMember(MemberJoinDTO dto);
    String oauthLoginProcess(HttpServletRequest request, OauthLoginDTO dto);

    String logout(HttpServletRequest request);
}
