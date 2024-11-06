package artgarden.server.member.service;

import artgarden.server.member.entity.Member;
import artgarden.server.member.entity.dto.*;
import artgarden.server.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@AllArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public String insertMember(MemberJoinDTO dto) {
        Member member = new Member(dto);
        String result = "";
        MemberViewDTO chkmember = memberRepository.findMemberByLoginidNoDelete(dto.getLoginid());
        if(chkmember == null){
            memberRepository.save(member);
            result = "ProcessSuccess";
        } else{
            result = "Duplicate.Loginid";
        }
        log.info(result);
        return result;
    }

    public MemberViewDTO selectMemberByLoginID(String loginid, HttpServletRequest request){

        return memberRepository.findMemberByLoginid(loginid);
    }

    public MemberViewDTO selectMemberByNickname(String nickname, HttpServletRequest request){

        return memberRepository.findMemberByNickname(nickname);
    }

    public MemberViewDTO selectMemberByLoginIDNoDelete(String loginid, HttpServletRequest request){

        return memberRepository.findMemberByLoginidNoDelete(loginid);
    }

    @Override
    public MemberViewDTO loginProcess(HttpServletRequest request, MemberLoginDTO dto) {
        String result = "ProcessSuccess";
        MemberViewDTO member = memberRepository.findMemberByLoginidPassword(dto);
        if(member == null){
            member = new MemberViewDTO();
            member.setMsg("Not.Matched");
        } else{
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30*60);
            session.setAttribute("memberid", dto.getLoginid());
        }

        return member;
    }

    @Override
    @Transactional
    public MemberViewDTO oauthLoginProcess(HttpServletRequest request, OauthLoginDTO dto) {
        String result = "";

        MemberViewDTO chkmember = memberRepository.findMemberByLoginid(dto.getLoginid());
        if(chkmember == null){  //신규가입하는 회원일 경우 회원 정보 insert 이후 다시 조회
            dto.setNickname(generateRandomNickname(7));
            Member member = new Member(dto);
            memberRepository.save(member);
            chkmember = memberRepository.findMemberByLoginid(dto.getLoginid());
        }

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30*60);
        session.setAttribute("memberid", chkmember.getLoginid());

        return chkmember;
    }

    private String generateRandomNickname(int length){
        String Characters = "abcdfghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        do{
            for(int i = 0; i< length; i++){
                int randomIndex = random.nextInt(Characters.length());
                sb.append(Characters.charAt(randomIndex));
            }
        } while(memberRepository.findMemberByNickname(sb.toString()) != null);

        return sb.toString();
    }

    @Override
    @Transactional
    public String updateMember(HttpServletRequest request, MemberUpdateDTO dto) {
        String result = "ProcessSuccess";
        HttpSession session = request.getSession();
        String memberid = (String)session.getAttribute("memberid");
        MemberViewDTO chkmember = memberRepository.findMemberByLoginid(memberid);

        if(memberid == null){
            return "Required.Login";
        }
        if(!dto.getLoginid().equals(memberid)){
            result = "Other.User";
        }
        if(chkmember == null){
            result = "NotFound.Loginid";
        }

        if(result.equals("ProcessSuccess")){
            memberRepository.updateMember(dto);
        }

        return result;
    }

    @Override
    public String logout(HttpServletRequest request) {
        String result = "";
        HttpSession session = request.getSession();
        session.invalidate();
        result = "ProcessSuccess";
        return result;
    }

    @Override
    @Transactional
    public String deleteMember(String loginid, HttpServletRequest request) {
        String result = "ProcessSuccess";
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");
        MemberViewDTO chkmember = memberRepository.findMemberByLoginid(memberid);
        if(!memberid.equals(loginid)){
            result = "Other.User";
        } else if(chkmember == null){
            result = "NotFound.Loginid";
        } else{
            memberRepository.deleteMember(loginid);
        }

        return result;
    }
}
