package teachin.server.service;

import org.springframework.stereotype.Service;
import teachin.server.entity.Discipline;
import teachin.server.entity.Teacher;
import teachin.server.exception.NotFoundException;
import teachin.server.repo.DisciplineRepo;

@Service
public class DisciplineService extends AbstractService<Discipline, DisciplineRepo> {
    protected DisciplineService(DisciplineRepo repo) {
        super(repo);
    }

    public Iterable<Teacher> getAllTeachersEngagedInDiscipline(String discipline) throws NotFoundException {
        return repo.findByName(discipline)
                .orElseThrow(() -> new NotFoundException("Дисциплина с названием %s отсутствует в системе".formatted(discipline)))
                .getTeachers();
    }
}
