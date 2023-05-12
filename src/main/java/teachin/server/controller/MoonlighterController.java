package teachin.server.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teachin.server.entity.Moonlighter;
import teachin.server.service.MoonlighterService;

@Api(description = "Управление совместительствами")
@RestController
@RequestMapping("api/v1/moonlighter")
public class MoonlighterController extends AbstractController<Moonlighter, MoonlighterService> {
    protected MoonlighterController(MoonlighterService service) {
        super(service);
    }
}
