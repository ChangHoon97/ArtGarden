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
public class OauthLoginDTO {
    @NotBlank(message = "로그인 아이디값은 필수입니다.")
    private String loginid;
}
