package artgarden.server.member.entity.dto;

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

    private String loginId;
    private String password;
    private String name;
    private LocalDate birthday;
    private String celno;
    private String email;
    private String nickname;
    private String addr1;
    private String addr2;
    private String addr3;
}
