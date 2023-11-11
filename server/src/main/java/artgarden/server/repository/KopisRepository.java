package artgarden.server.repository;

import artgarden.server.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KopisRepository extends JpaRepository<Performance, Long> {

    List<Performance> findAllByOrderByEndDateAsc();

    Performance findById(String id);


}
