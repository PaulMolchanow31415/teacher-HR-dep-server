package teachin.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Discipline;
import teachin.server.service.DisciplineService;

@RestController
@RequestMapping("api/v1/discipline")
public class DisciplineController extends AbstractController<Discipline, DisciplineService> {
    protected DisciplineController(DisciplineService service) {
        super(service);
    }
}
