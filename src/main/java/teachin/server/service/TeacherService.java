package teachin.server.service;

import org.springframework.stereotype.Service;
import teachin.server.entity.Teacher;
import teachin.server.repo.TeacherRepo;

@Service
public class TeacherService extends AbstractService<Teacher, TeacherRepo> {
    protected TeacherService(TeacherRepo repo) {
        super(repo);
    }
}
