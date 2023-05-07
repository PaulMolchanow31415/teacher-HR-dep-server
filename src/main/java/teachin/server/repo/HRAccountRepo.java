package teachin.server.repo;

import org.springframework.stereotype.Repository;
import teachin.server.entity.HRAccount;

import java.util.Optional;

@Repository
public interface HRAccountRepo extends CommonRepo<HRAccount> {
    Optional<HRAccount> findByUsername(String username);
}
