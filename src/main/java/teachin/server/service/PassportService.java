package teachin.server.service;

import org.springframework.stereotype.Service;
import teachin.server.entity.Passport;
import teachin.server.repo.PassportRepo;

@Service
public class PassportService extends AbstractService<Passport, PassportRepo> {
    protected PassportService(PassportRepo repo) {
        super(repo);
    }
}
