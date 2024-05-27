package artgarden.server.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

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
    private LocalDate regdt;
    @ColumnDefault("false")
    private boolean delyn;

    protected Member() {
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
