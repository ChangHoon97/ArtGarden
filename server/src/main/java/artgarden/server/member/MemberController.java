package artgarden.server.member;

import artgarden.server.member.entity.dto.MemberDeleteDTO;
import artgarden.server.member.entity.dto.MemberJoinDTO;
import artgarden.server.member.entity.dto.MemberViewDTO;
import artgarden.server.member.entity.dto.OauthLoginDTO;
import artgarden.server.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Member", description = "회원 API")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "/join")
    @PostMapping("/join")
    public ResponseEntity<String> joinMember(@Valid @RequestBody MemberJoinDTO dto){
        String result = "";
        result = memberService.insertMember(dto);
        if(!result.equals("ProcessSuccess")){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "id중복확인", description = "/chkLoginid")
    @GetMapping(value = "chkLoginid")
    public ResponseEntity<String> chkLoginIdDup(@RequestParam String loginid, HttpServletRequest request){
        String result = "false";
        MemberViewDTO member = memberService.selectMemberByLoginID(loginid,request);
        if(member != null){
            result = "true";
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "SNS로그인", description = "/oauthLoginProcess")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/oauthLoginProcess")
    public ResponseEntity<String> oauthLoginProcess(HttpServletRequest request, @RequestBody OauthLoginDTO dto){
        String result = "";
        result = memberService.oauthLoginProcess(request, dto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "회원정보상세", description = "/members")
    @GetMapping("/members")
    public ResponseEntity<?> findMember(HttpServletRequest request){
        HttpSession session = request.getSession();
        String memberid = (String)session.getAttribute("memberid");
        MemberViewDTO dto;
        if(memberid != null){
            dto = memberService.selectMemberByLoginID(memberid, request);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Required.Login");
        }

        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "회원정보수정", description = "/members")
    @PatchMapping("/members")
    public ResponseEntity<String> updateMember(HttpServletRequest request, @Valid @RequestBody MemberViewDTO dto){
        String result = "";

        result = memberService.updateMember(request, dto);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "회원탈퇴", description = "/leaveId")
    @PostMapping("/leaveId")
    public ResponseEntity<?> leaveId(@Valid @RequestBody MemberDeleteDTO dto, HttpServletRequest request){
        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("memberid");
        String result = "";
        if(memberid != null){
            result = memberService.deleteMember(dto.getLoginid(), request);
        } else{
            result = "Required.Login";
        }

        if(result.equals("ProcessSuccess")){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @Operation(summary = "로그아웃", description = "/logout")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/memberLogout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        String result = "";
        result = memberService.logout(request);
        return ResponseEntity.ok(result);
    }

}
