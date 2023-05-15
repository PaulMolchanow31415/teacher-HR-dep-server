package teachin.server.service;

import org.springframework.stereotype.Service;
import teachin.server.entity.Discipline;
import teachin.server.entity.ScientificDegree;
import teachin.server.entity.Teacher;
import teachin.server.entity.Vacancy;
import teachin.server.exception.NotFoundException;
import teachin.server.repo.TeacherRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService extends AbstractService<Teacher, TeacherRepo> {
    protected TeacherService(TeacherRepo repo) {
        super(repo);
    }

    public Iterable<Teacher> getAllBySciDegree(String degree) throws NotFoundException {
        ScientificDegree scientificDegree;
        try {
            scientificDegree = ScientificDegree.valueOf(degree);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Такой ученой степени не существует");
        }
        return repo.findAllByScientificDegree(scientificDegree);
    }

    public Iterable<Teacher> getByCertainPosition(String position) {
        return repo.findAllByVacancyName(position);
    }

    public Iterable<Discipline> getAllDisciplines(String fio) throws NotFoundException {
        return repo.findByFIO(fio)
                .orElseThrow(() -> new NotFoundException("Преподавателя с такими данными нет в системе"))
                .getVacancy().getDisciplines();
    }

    public Iterable<Teacher> getPartTimers() {
        return repo.findTeachersWithNotEmptySocialWork();
    }

    public List<Teacher> getTeachersEngagedInVacancies(List<Vacancy> vacancies) {
        List<Teacher> teachers = new ArrayList<>();

        for (Vacancy v : vacancies)
            teachers.add(repo.findByVacancyName(v.getName()));

        return teachers;
    }

    public Teacher getByVacancyName(String name) {
        return repo.findByVacancyName(name);
    }

    public Iterable<Teacher> getAllPartTimers(String work) {
        return repo.findAllBySocialWork(work);
    }

    public Iterable<Teacher> getFullTimers() {
        return repo.findAllWithEmptySocialWork();
    }

    public Iterable<Teacher> getAllVacationing(Integer hours) {
        return repo.findAllByHoursLessThan(hours);
    }

    public Iterable<Teacher> getAllProcessing(Integer hours) {
        return repo.findAllByHoursGreaterThan(hours);
    }

    public Teacher getByFIO(String fio) throws NotFoundException {
        return repo.findByFIO(fio).orElseThrow(() ->
                new NotFoundException("Пользователь с именем %s отсутствует в системе".formatted(fio))
        );
    }
}
