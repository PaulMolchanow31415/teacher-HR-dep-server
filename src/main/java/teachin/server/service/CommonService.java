package teachin.server.service;

import teachin.server.entity.AbstractEntity;
import teachin.server.exception.NotFoundException;
import teachin.server.exception.ValidationException;

public interface CommonService<E extends AbstractEntity> {
    void write(E entity) throws ValidationException, ClassNotFoundException;

    Iterable<E> getAll();

    void erase(E entity) throws NotFoundException;

    void erase(Long id) throws NotFoundException;
}
