package artgarden.server.member.service;

import artgarden.server.member.entity.dto.MemberJoinDTO;
import artgarden.server.member.entity.dto.MemberViewDTO;
import artgarden.server.member.entity.dto.OauthLoginDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {
    public String insertMember(MemberJoinDTO dto);

    MemberViewDTO selectMemberByLoginID(String loginid, HttpServletRequest request);
    String oauthLoginProcess(HttpServletRequest request, OauthLoginDTO dto);

    String updateMember(HttpServletRequest request, MemberViewDTO dto);

    String logout(HttpServletRequest request);
}
