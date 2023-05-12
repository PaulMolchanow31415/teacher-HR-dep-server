package teachin.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Discipline;
import teachin.server.res.BaseRes;
import teachin.server.res.ListRes;
import teachin.server.service.DisciplineService;

@RestController
@RequestMapping("api/v1/discipline")
@Api(description = "Управление дисциплинами")
public class DisciplineController extends AbstractController<Discipline, DisciplineService> {
    protected DisciplineController(DisciplineService service) {
        super(service);
    }

    /**
     * 5. Возварщает всех преподавателей, занятых конкретной дисциплиной
     */
    @ApiOperation("Возварщает всех преподавателей, занятых конкретной дисциплиной")
    @GetMapping("/list_of_engaged_teachers")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<?> listEngagedInDiscipline(@RequestParam String discipline) {
        try {
            return ResponseEntity.ok(new ListRes<>(
                    "Список преподавателей, занятых дисциплиной \"%s\"".formatted(discipline),
                    service.getAllTeachersEngagedInDiscipline(discipline))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }
}
