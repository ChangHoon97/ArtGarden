package artgarden.server.member.service;

import artgarden.server.member.entity.Member;
import artgarden.server.member.entity.dto.*;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {
    public String insertMember(MemberJoinDTO dto);

    MemberViewDTO selectMemberByLoginID(String loginid, HttpServletRequest request);

    MemberViewDTO selectMemberByNickname(String nickname, HttpServletRequest request);

    MemberViewDTO selectMemberByLoginIDNoDelete(String loginid, HttpServletRequest request);

    MemberViewDTO loginProcess(HttpServletRequest request, MemberLoginDTO dto);
    MemberViewDTO oauthLoginProcess(HttpServletRequest request, OauthLoginDTO dto);

    String updateMember(HttpServletRequest request, MemberUpdateDTO dto);

    String logout(HttpServletRequest request);

    String deleteMember(String loginid, HttpServletRequest request);
}
