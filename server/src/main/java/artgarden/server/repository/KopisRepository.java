package artgarden.server.repository;

import artgarden.server.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KopisRepository extends JpaRepository<Performance, Long> {

    List<Performance> findAllByOrderByEndDateAsc();


}
