package artgarden.server.member;

import artgarden.server.member.entity.dto.MemberJoinDTO;
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
    public ResponseEntity<String> chkLoginIdDup(@RequestParam String loginid){
        String result = "";
        result = memberService.selectMemberByLoginID(loginid);
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

    @Operation(summary = "로그아웃", description = "/logout")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        String result = "ProcessFail";
        result = memberService.logout(request);
        return ResponseEntity.ok(result);
    }
}
