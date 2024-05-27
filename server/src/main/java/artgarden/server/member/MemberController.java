package artgarden.server.member;

import artgarden.server.member.entity.dto.MemberJoinDTO;
import artgarden.server.member.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Member", description = "회원 API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<String> joinMember(@RequestBody MemberJoinDTO dto){
        String result = "";
        result = memberService.insertMember(dto);
        return ResponseEntity.ok(result);
    }
}
