package teachin.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Teacher;
import teachin.server.service.TeacherService;

@RestController
@RequestMapping("api/v1/teacher")
public class TeacherController extends AbstractController<Teacher, TeacherService> {
    protected TeacherController(TeacherService service) {
        super(service);
    }
}
