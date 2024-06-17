package artgarden.server.member.service;

import artgarden.server.member.entity.Member;
import artgarden.server.member.entity.dto.MemberJoinDTO;
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
        memberRepository.save(member);
        return "ProcessSuccess";
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
    public String logout(HttpServletRequest request) {
        String result = "";
        HttpSession session = request.getSession();
        session.invalidate();
        result = "ProcessSuccess";
        return result;
    }
}
