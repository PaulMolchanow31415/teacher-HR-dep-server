package teachin.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import teachin.server.entity.AbstractEntity;

@NoRepositoryBean
public interface CommonRepo<E extends AbstractEntity> extends CrudRepository<E, Long> {
}
