package artgarden.server.member.entity.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDTO {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z0-9]{5,20}$")   //영문 소문자와 숫자만을 포함해야 하고 5~20자
    private String loginid;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[$~!@%*#^?&()\\-_=+])[a-zA-Z\\d$~!@%*#^?&()\\-_=+]{8,16}$")   //8~16자의 길이이고 영문 대문자, 영문 소문자, 숫자, 특수문자를 무조건 포함하여야 한다. 특수문자는 $ ~ ! @ % * # ^ ? & ( ) - _ = +만 사용가능한다.
    private String password;

    @Pattern(regexp = "^[가-힣a-zA-Z]{1,30}$")
    private String name;

    @Past
    /*@Pattern(regexp = "\"^(19|20)\\\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\\\d|3[01])$\"")*/
    private LocalDate birthday;

    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    private String celno;

    @Email
    private String email;

    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$")
    private String nickname;

    @Pattern(regexp = "^.{1,100}$")
    private String addr1;

    @Pattern(regexp = "^.{1,100}$")
    private String addr2;

    @Pattern(regexp = "^.{0,100}$")
    private String addr3;
}
