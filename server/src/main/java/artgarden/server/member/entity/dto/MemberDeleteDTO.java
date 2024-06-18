package artgarden.server.member.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDeleteDTO {
    @NotBlank(message = "회원 탈퇴 할 아이디는 필수 입력값잆니다.")
    private String loginid;
}
