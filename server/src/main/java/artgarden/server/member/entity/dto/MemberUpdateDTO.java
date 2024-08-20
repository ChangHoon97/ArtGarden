package artgarden.server.member.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDTO {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String loginid;

    @NotBlank(message = "별명은 필수 입력 값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "2~10자 이내의 한글,영문으로 이루어져야하고 공백이 없어야 합니다.")
    private String nickname;
}
