package teachin.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Passport;
import teachin.server.service.PassportService;

@RestController
@RequestMapping("api/v1/passport")
public class PassportController extends AbstractController<Passport, PassportService> {
    protected PassportController(PassportService service) {
        super(service);
    }
}
