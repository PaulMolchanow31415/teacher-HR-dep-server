package teachin.server.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Passport;
import teachin.server.service.PassportService;

@Api(description = "Управление паспортами")
@RestController
@RequestMapping("api/v1/passport")
public class PassportController extends AbstractController<Passport, PassportService> {
    protected PassportController(PassportService service) {
        super(service);
    }
}
