package artgarden.server.common.repository;

import artgarden.server.common.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {

    @Query("SELECT c FROM Code c " +
            "WHERE c.uppcd = :uppcd AND c.cddepth = :cddepth AND c.cdtype = :cdtype " +
            "AND c.delyn = false AND c.useyn = true " +
            "ORDER BY c.orderby")
    List<Code> getCodeList(@Param("cdtype") String cdtype, @Param("uppcd") String uppcd, @Param("cddepth") Integer cddepth);
}
