package artgarden.server.service;

import artgarden.server.entity.TestEntity;
import artgarden.server.repository.TestReposiotry;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final TestReposiotry testReposiotry;

    public void testSave(TestEntity test){
        testReposiotry.save(test);
    }
}
