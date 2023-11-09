package artgarden.server.repository;

import artgarden.server.entity.Perforamnce;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KopisRepository extends JpaRepository<Perforamnce, Long> {

    List<Perforamnce> findAllByOrderByEndDateAsc();


}
