package teachin.server.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Vacancy;
import teachin.server.service.VacancyService;

@Api(description = "Управление вакансиями")
@RestController
@RequestMapping("api/v1/vacancy")
public class VacancyController extends AbstractController<Vacancy, VacancyService> {
    protected VacancyController(VacancyService service) {
        super(service);
    }
}
