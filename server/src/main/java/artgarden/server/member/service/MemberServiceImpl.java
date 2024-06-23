package artgarden.server.member.service;

import artgarden.server.member.entity.Member;
import artgarden.server.member.entity.dto.MemberJoinDTO;
import artgarden.server.member.entity.dto.MemberLoginDTO;
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
        MemberViewDTO chkmember = memberRepository.fdinAllMember(dto.getLoginid());
        if(chkmember == null){
            memberRepository.save(member);
            result = "ProcessSuccess";
        } else{
            result = "Duplicate.Loginid";
        }
        return result;
    }

    public MemberViewDTO selectMemberByLoginID(String loginid, HttpServletRequest request){

        MemberViewDTO member = memberRepository.findMemberByLoginid(loginid);

        return member;
    }

    @Override
    public String loginProcess(HttpServletRequest request, MemberLoginDTO dto) {
        String result = "ProcessSuccess";
        Member member = memberRepository.findMemberByLoginidPassword(dto);
        if(member == null){
            result = "Not.Matched";
        } else{
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30*60);
            session.setAttribute("memberid", dto.getLoginid());
            String memberid = (String) session.getAttribute("memberid");
            log.info("============== 로그인 성공 : " + memberid + " ==============");
        }

        return result;
    }

    @Override
    public String oauthLoginProcess(HttpServletRequest request, OauthLoginDTO dto) {
        String result = "";
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30*60);
        session.setAttribute("memberid", dto.getLoginid());
        String memberid = (String) session.getAttribute("memberid");
        log.info("============== 로그인 성공 : " + memberid + " ==============");
        result = "ProcessSuccess";
        return result;
    }

    @Override
    @Transactional
    public String updateMember(HttpServletRequest request, MemberViewDTO dto) {
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
