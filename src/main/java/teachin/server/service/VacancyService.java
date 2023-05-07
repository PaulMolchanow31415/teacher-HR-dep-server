package teachin.server.service;

import org.springframework.stereotype.Service;
import teachin.server.entity.Vacancy;
import teachin.server.repo.VacancyRepo;

@Service
public class VacancyService extends AbstractService<Vacancy, VacancyRepo> {
    protected VacancyService(VacancyRepo repo) {
        super(repo);
    }
}
