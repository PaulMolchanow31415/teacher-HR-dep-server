package teachin.server.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Discipline;
import teachin.server.service.DisciplineService;

@RestController
@RequestMapping("api/v1/discipline")
@Api(description = "Управление дисциплинами")
public class DisciplineController extends AbstractController<Discipline, DisciplineService> {
    protected DisciplineController(DisciplineService service) {
        super(service);
    }
}
