package teachin.server.repo;

import org.springframework.stereotype.Repository;
import teachin.server.entity.Discipline;

import java.util.Optional;

@Repository
public interface DisciplineRepo extends CommonRepo<Discipline> {
    Optional<Discipline> findByName(String name);
}
