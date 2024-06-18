package artgarden.server.member.service;

import artgarden.server.member.entity.Member;
import artgarden.server.member.entity.dto.MemberJoinDTO;
import artgarden.server.member.entity.dto.MemberViewDTO;
import artgarden.server.member.entity.dto.OauthLoginDTO;
import artgarden.server.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        MemberViewDTO chkmember = memberRepository.findMemberByLoginid(dto.getLoginid());
        if(chkmember == null){
            memberRepository.save(member);
            result = "ProcessSuccess";
        } else{
            result = "Duplicate.LoginID";
        }
        return result;
    }

    public MemberViewDTO selectMemberByLoginID(String loginid, HttpServletRequest request){

        MemberViewDTO member = memberRepository.findMemberByLoginid(loginid);

        return member;
    }

    @Override
    public String oauthLoginProcess(HttpServletRequest request, OauthLoginDTO dto) {
        String result = "";
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30*60);
        session.setAttribute("memberid", dto.getId());
        String memberid = (String) session.getAttribute("memberid");
        log.info("============== 로그인 성공 : " + memberid + " ==============");
        result = "LoginSuccess";
        return result;
    }

    @Override
    @Transactional
    public String updateMember(HttpServletRequest request, MemberViewDTO dto) {
        String result = "ProcessSuccess";
        HttpSession session = request.getSession();
        String memberid = (String)session.getAttribute("memberid");
        MemberViewDTO chkmember = memberRepository.findMemberByLoginid(memberid);

        if(!dto.getLoginid().equals(memberid)){
            result = "Different.Loginid";
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
        String sessionMemberid = (String) session.getAttribute("memberid");
        System.out.println("sessionMemberid : " + sessionMemberid);
        System.out.println("loginid : " + loginid);
        if(sessionMemberid.equals(loginid)){
            memberRepository.deleteMember(loginid);
        } else{
            result = "Other.User";
        }

        return result;
    }
}
