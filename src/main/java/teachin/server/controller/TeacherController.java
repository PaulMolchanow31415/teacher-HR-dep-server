package teachin.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Teacher;
import teachin.server.res.BaseRes;
import teachin.server.res.EntityRes;
import teachin.server.res.ListRes;
import teachin.server.service.TeacherService;

@RestController
@RequestMapping("api/v1/teacher")
public class TeacherController extends AbstractController<Teacher, TeacherService> {
    protected TeacherController(TeacherService service) {
        super(service);
    }

    /**
     * 1. Возварщает список всех преподавателей, имеющих ученую степень кандидата или доктора наук
     */
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

    /**
     * 2. Возварщает список преподавателей, имеющих определенную должность
     */
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

    /**
     * 3. Возварщает список всех дисциплин, на которых работают преподаватель по его имени,фамилии или отчеству
     */
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

    /**
     * 4. Возварщает список преподавателей, задействованных в общественной работе
     */
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

    /**
     * 6. Возварщает сотрудника с определенной должностью
     */
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

    /**
     * 7. Возварщает сотрудников, работающих на определенное совместительство
     */
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

    /**
     * 8. Возварщает сотрудников, работающих по полному рабочему графику
     */
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

    /**
     * 9. Возварщает всех преподавателей, у которых нагрузка меньше заданной
     */
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

    /**
     * 10. Возварщает всех преподавателей, у которых нагрузка превысила заданную
     */
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

    /**
     * 11. Возварщает сотрудников по фамилии
     */
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
