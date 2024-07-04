package artgarden.server.member.repository;

import artgarden.server.member.entity.Member;
import artgarden.server.member.entity.dto.MemberLoginDTO;
import artgarden.server.member.entity.dto.MemberViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //Optional<Member> findByOauthId(String id);

    @Query("SELECT new artgarden.server.member.entity.dto.MemberViewDTO(m) " +
            "FROM Member m " +
            "WHERE m.loginid = :loginid AND m.delyn = false")
    MemberViewDTO findMemberByLoginid(@Param("loginid") String loginid);

    @Query("SELECT new artgarden.server.member.entity.dto.MemberViewDTO(m) " +
            "FROM Member m " +
            "WHERE m.nickname = :nickname AND m.delyn = false")
    MemberViewDTO findMemberByNickname(@Param("nickname") String nickname);

    @Query("SELECT new artgarden.server.member.entity.dto.MemberViewDTO(m) " +
            "FROM Member m " +
            "WHERE m.loginid = :loginid")
    MemberViewDTO fdinAllMember(@Param("loginid") String loginid);

    @Query("SELECT m " +
            "FROM Member m " +
            "WHERE m.loginid = :#{#dto.loginid} AND m.password = :#{#dto.password} AND m.delyn = false")
    Member findMemberByLoginidPassword(@Param("dto")MemberLoginDTO dto);

    @Query("UPDATE Member m " +
            "SET m.name = :#{#dto.name}, m.email = :#{#dto.email}, m.nickname = :#{#dto.nickname} " +
            "WHERE m.loginid = :#{#dto.loginid}")
    @Modifying
    void updateMember(@Param("dto") MemberViewDTO dto);

    @Query("UPDATE Member m SET m.delyn = true WHERE m.loginid = :loginid")
    @Modifying
    void deleteMember(@Param("loginid") String loginid);

}

