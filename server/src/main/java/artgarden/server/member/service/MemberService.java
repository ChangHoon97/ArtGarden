package artgarden.server.member.service;

import artgarden.server.member.entity.Member;
import artgarden.server.member.entity.dto.MemberJoinDTO;
import artgarden.server.member.entity.dto.MemberLoginDTO;
import artgarden.server.member.entity.dto.MemberViewDTO;
import artgarden.server.member.entity.dto.OauthLoginDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {
    public String insertMember(MemberJoinDTO dto);

    MemberViewDTO selectMemberByLoginID(String loginid, HttpServletRequest request);

    MemberViewDTO selectMemberByNickname(String nickname, HttpServletRequest request);

    MemberViewDTO selectMemberByLoginIDNoDelete(String loginid, HttpServletRequest request);

    String loginProcess(HttpServletRequest request, MemberLoginDTO dto);
    String oauthLoginProcess(HttpServletRequest request, OauthLoginDTO dto);

    String updateMember(HttpServletRequest request, MemberViewDTO dto);

    String logout(HttpServletRequest request);

    String deleteMember(String loginid, HttpServletRequest request);
}
