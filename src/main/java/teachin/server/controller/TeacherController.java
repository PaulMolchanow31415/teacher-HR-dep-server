package teachin.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Teacher;
import teachin.server.entity.Vacancy;
import teachin.server.res.BaseRes;
import teachin.server.res.EntityRes;
import teachin.server.res.ListRes;
import teachin.server.service.TeacherService;
import teachin.server.service.VacancyService;

import java.util.List;

@Api(description = "Управление данными преподавателей")
@RestController
@RequestMapping("api/v1/teacher")
public class TeacherController extends AbstractController<Teacher, TeacherService> {
    private final VacancyService vacancyService;

    protected TeacherController(TeacherService service, VacancyService vacancyService) {
        super(service);
        this.vacancyService = vacancyService;
    }

    @ApiOperation("1. Возварщает список всех преподавателей, имеющих ученую степень кандидата или доктора наук")
    @GetMapping("/find_all_by_sci-degree")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<?> listFromSciDegree(@RequestParam String degree) {
        try {
            return ResponseEntity.ok(new ListRes<>(
                    "Список преподавателей, имеющих ученую степень",
                    service.getAllBySciDegree(degree))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("2. Возварщает список преподавателей, имеющих определенную должность")
    @GetMapping("/find_all_by_certain_position")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<?> findByCertainPosition(@RequestParam String position) {
        try {
            return ResponseEntity.ok(new ListRes<>(
                    "Список преподавателей, имеющих определенную должность",
                    service.getByCertainPosition(position))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("3. Возварщает список всех дисциплин, на которых работают преподаватель по его имени,фамилии или отчеству")
    @GetMapping("/list_disciplines_by_fio")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<?> listDisciplinesByFIO(@RequestParam String fio) {
        try {
            return ResponseEntity.ok(new ListRes<>(
                    "Список дисциплин",
                    service.getAllDisciplines(fio))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("4. Возварщает список преподавателей, задействованных в общественной работе")
    @GetMapping("/list_part-timers")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<?> listPartTimers() {
        try {
            return ResponseEntity.ok(new ListRes<>(
                    "Список задействованных в общественной работе",
                    service.getPartTimers())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("5. Возварщает всех преподавателей, занятых конкретной дисциплиной")
    @GetMapping("/list_of_engaged_teachers")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<?> listEngagedInDiscipline(@RequestParam String discipline) {
        try {
            List<Vacancy> vacancies = vacancyService.getAllVacanciesContainsDisciplineName(discipline);

            return ResponseEntity.ok(new ListRes<>(
                    "Список преподавателей, занятых дисциплиной \"%s\"".formatted(discipline),
                    service.getTeachersEngagedInVacancies(vacancies)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("6. Возварщает сотрудника с определенной должностью")
    @GetMapping("/find_by_certain_position")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<EntityRes<?>> getFromCertainPosition(@RequestParam String position) {
        try {
            return ResponseEntity.ok(new EntityRes<>(
                    true,
                    "Преподаватель с должностью %s".formatted(position),
                    service.getByVacancyName(position)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new EntityRes<>(false, e.getMessage(), null));
        }
    }

    @ApiOperation("7. Возварщает сотрудников, работающих на определенное совместительство")
    @GetMapping("/find_part-timers")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<?> listPartTimers(@RequestParam String work) {
        try {
            return ResponseEntity.ok(new ListRes<>(
                    "Список частично занятых",
                    service.getAllPartTimers(work)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("8. Возварщает сотрудников, работающих по полному рабочему графику")
    @GetMapping("/find_full-timers")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<?> listFullTimers() {
        try {
            return ResponseEntity.ok(new ListRes<>(
                    "Список полностью занятых",
                    service.getFullTimers()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("9. Возварщает всех преподавателей, у которых нагрузка меньше заданной")
    @GetMapping("/workload_less_than")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<?> getVacationing(@RequestParam Integer hours) {
        try {
            return ResponseEntity.ok(new ListRes<>(
                    "Список преподавателей, у которых нагрузка меньше %d часов".formatted(hours),
                    service.getAllVacationing(hours)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("10. Возварщает всех преподавателей, у которых нагрузка превысила заданную")
    @GetMapping("/workload_exceeded_than")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<?> getProcessing(@RequestParam Integer hours) {
        try {
            return ResponseEntity.ok(new ListRes<>(
                    "Список преподавателей, у которых нагрузка превысила %d часов".formatted(hours),
                    service.getAllProcessing(hours)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("11. Возварщает сотрудников по фамилии")
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<EntityRes<?>> findByFIO(@RequestParam String fio) {
        try {
            return ResponseEntity.ok(new EntityRes<>(
                    true,
                    "Преподаватель с именем %s".formatted(fio),
                    service.getByFIO(fio)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new EntityRes<>(false, e.getMessage(), null));
        }
    }
}
