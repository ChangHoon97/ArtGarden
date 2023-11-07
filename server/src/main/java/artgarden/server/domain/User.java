package artgarden.server.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String user_password;

    @Column(nullable = false)
    private String user_name;

    private Date user_birth;

    @Column(unique = true, nullable = false)
    private String user_email;

    private String user_gender;

    @Column(unique = true)
    private String user_phone;

    private Date user_created_at;


}
