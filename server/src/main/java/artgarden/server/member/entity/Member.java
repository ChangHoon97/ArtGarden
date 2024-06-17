package artgarden.server.member.entity;

import artgarden.server.member.entity.dto.MemberJoinDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginid;
    private String password;
    private String name;
    private LocalDate birthday;
    private String celno;
    private String email;
    private String nickname;
    private String addr1;
    private String addr2;
    private String addr3;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime regdt;
    @ColumnDefault("false")
    private boolean delyn;

    protected Member() {
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public Member(MemberJoinDTO dto){
        this.loginid = dto.getLoginid();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.birthday = dto.getBirthday();
        this.celno = dto.getCelno();
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.addr1 = dto.getAddr1();
        this.addr2 = dto.getAddr2();
        this.addr3 = dto.getAddr3();
        this.imageUrl = null;
        this.role = Role.USER;
        this.regdt = LocalDateTime.now();
        this.delyn = false;
    }

}
