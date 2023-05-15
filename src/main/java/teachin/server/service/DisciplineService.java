package teachin.server.service;

import org.springframework.stereotype.Service;
import teachin.server.entity.Discipline;
import teachin.server.repo.DisciplineRepo;

@Service
public class DisciplineService extends AbstractService<Discipline, DisciplineRepo> {
    protected DisciplineService(DisciplineRepo repo) {
        super(repo);
    }
}
