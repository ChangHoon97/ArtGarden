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
public class MemberLoginDTO {
    @NotBlank(message = "로그인 아이디는 필수 입력값입니다.")
    private String loginid;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;
}
