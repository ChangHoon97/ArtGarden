package artgarden.server.exhibit.repository;

import artgarden.server.exhibit.entity.Exhibit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {
}
