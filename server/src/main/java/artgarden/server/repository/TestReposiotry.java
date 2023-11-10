package artgarden.server.repository;

import artgarden.server.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestReposiotry extends JpaRepository<TestEntity, Long> {
}
