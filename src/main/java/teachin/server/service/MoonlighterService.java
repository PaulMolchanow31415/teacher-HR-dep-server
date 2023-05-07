package teachin.server.service;

import org.springframework.stereotype.Service;
import teachin.server.entity.Moonlighter;
import teachin.server.repo.MoonlighterRepo;

@Service
public class MoonlighterService extends AbstractService<Moonlighter, MoonlighterRepo> {
    protected MoonlighterService(MoonlighterRepo repo) {
        super(repo);
    }
}
