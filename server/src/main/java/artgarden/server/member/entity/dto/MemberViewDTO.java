package artgarden.server.member.entity.dto;

import artgarden.server.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberViewDTO {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z0-9]{5,20}$", message = "5~20자 이내의 영문 소문자와 숫자만으로 이루어져야합니다.")   //영문 소문자와 숫자만을 포함해야 하고 5~20자
    private String loginid;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z]{1,30}$", message = "1~30자 이내의 한글,영문으로 이루어져야합니다.")
    private String name;

    /*@Past(message = "생년월일은 오늘보다 과거여야 합니다.")
    *//*@Pattern(regexp = "\"^(19|20)\\\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\\\d|3[01])$\"")*//*
    private LocalDate birthday;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호는 010-000-0000 혹은 010-0000-0000 형식이어야 합니다")
    private String celno;*/

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "올바르지 않은 이메일 형식입니다.")
    private String email;

    @NotBlank(message = "별명은 필수 입력 값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "2~10자 이내의 한글,영문으로 이루어져야합니다.")
    private String nickname;

    MemberViewDTO (Member member){
        this.loginid = member.getLoginid();
        this.name = member.getName();
        //this.birthday = member.getBirthday();
        //this.celno = member.getCelno();
        //this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
