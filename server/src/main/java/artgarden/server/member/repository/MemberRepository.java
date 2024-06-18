package artgarden.server.member.repository;

import artgarden.server.member.entity.Member;
import artgarden.server.member.entity.dto.MemberJoinDTO;
import artgarden.server.member.entity.dto.MemberUpdateDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //Optional<Member> findByOauthId(String id);

    @Query("SELECT m " +
            "FROM Member m " +
            "WHERE m.loginid = :loginid")
    Member findMemberByLoginid(@Param("loginid") String loginid);

    @Query("UPDATE Member m " +
            "SET m.name = :#{#dto.name}, m.birthday = :#{#dto.birthday}, m.celno = :#{#dto.celno}, m.email = :#{#dto.email}, m.nickname = :#{#dto.nickname} " +
            "WHERE m.loginid = :#{#dto.loginid}")
    @Modifying
    void updateMember(@Param("dto") MemberUpdateDTO dto);

}

