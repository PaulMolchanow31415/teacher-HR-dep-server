package teachin.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Vacancy;
import teachin.server.service.VacancyService;

@RestController
@RequestMapping("api/v1/vacancy")
public class VacancyController extends AbstractController<Vacancy, VacancyService> {
    protected VacancyController(VacancyService service) {
        super(service);
    }
}
