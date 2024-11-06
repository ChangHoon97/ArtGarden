package artgarden.server.member.entity.dto;

import artgarden.server.member.entity.SnsType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OauthLoginDTO {
    @NotBlank(message = "로그인 아이디값은 필수입니다.")
    private String loginid;

    @NotBlank(message = "이름은 필수 값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수값입니다.")
    @Email(message = "이메일 형식에 맞게 제출하세요.")
    private String email;

    private String nickname;
    private SnsType snstype;

}
