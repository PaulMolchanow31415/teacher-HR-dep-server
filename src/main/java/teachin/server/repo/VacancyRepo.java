package teachin.server.repo;

import org.springframework.stereotype.Repository;
import teachin.server.entity.Vacancy;

import java.util.List;

@Repository
public interface VacancyRepo extends CommonRepo<Vacancy> {
    List<Vacancy> findByDisciplines_Name(String name);
}
