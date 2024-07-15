package artgarden.server.member;

import artgarden.server.common.util.UtilBean;
import artgarden.server.member.entity.dto.*;
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
    public ResponseEntity<?> joinMember(@Valid @RequestBody MemberJoinDTO dto){
        String result = "";
        result = memberService.insertMember(dto);
        if(!result.equals("ProcessSuccess")){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "id중복확인", description = "/chkLoginid, true면 중복 있음 false면 중복 없음")
    @GetMapping(value = "chkLoginid")
    public ResponseEntity<?> chkLoginIdDup(@RequestParam String loginid, HttpServletRequest request){
        String result = "false";
        MemberViewDTO member = memberService.selectMemberByLoginIDNoDelete(loginid,request);
        if(member != null){
            result = "true";
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "nickname중복확인", description = "/chkNickname, true면 중복 있음 false면 중복 없음")
    @GetMapping(value = "chkNickname")
    public ResponseEntity<?> chkNickname(@RequestParam String nickname, HttpServletRequest request){
        String result = "false";
        MemberViewDTO member = memberService.selectMemberByNickname(nickname,request);
        if(member != null){
            result = "true";
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "현재 로그인 여부확인", description = "/chkNickname, true면 로그인 상태 false면 비로그인 상태")
    @GetMapping(value = "chkLogin")
    public ResponseEntity<?> chkLogin(HttpServletRequest request){
        String result = "false";
        HttpSession session = request.getSession();
        String membernum = (String) session.getAttribute("memberid");
        if(membernum != null && !membernum.equals("")){
            result = "true";
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "SNS로그인", description = "/oauthLoginProcess")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/oauthLoginProcess")
    public ResponseEntity<?> oauthLoginProcess(HttpServletRequest request, @Valid @RequestBody OauthLoginDTO dto){
        MemberViewDTO result = memberService.oauthLoginProcess(request, dto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "일반로그인", description="/loginProcess")
    @PostMapping("/loginProcess")
    public ResponseEntity<?> loginProcess(HttpServletRequest request, @Valid @RequestBody MemberLoginDTO dto){

        MemberViewDTO result = memberService.loginProcess(request, dto);
        if(UtilBean.checkNullString(result.getMsg()).equals("Not.Matched")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.getMsg());
        }

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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Required.Login");
        }

        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "회원정보수정", description = "/members")
    @PatchMapping("/members")
    public ResponseEntity<?> updateMember(HttpServletRequest request, @Valid @RequestBody MemberViewDTO dto){
        String result = "";

        result = memberService.updateMember(request, dto);

        if(result.equals("Other.User")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        } else if(result.equals("NotFound.Loginid")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else if(result.equals("Required.Login")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

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

        if(result.equals("Other.User") || result.equals("Required.Login")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        } else if(result.equals("NotFound.Loginid")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "로그아웃", description = "/logout")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/memberLogout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        String result = "";
        result = memberService.logout(request);
        return ResponseEntity.ok(result);
    }

}
