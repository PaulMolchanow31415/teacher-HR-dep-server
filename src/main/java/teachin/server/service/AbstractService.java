package teachin.server.service;

import teachin.server.entity.AbstractEntity;
import teachin.server.exception.NotFoundException;
import teachin.server.exception.ValidationException;
import teachin.server.repo.CommonRepo;
import teachin.server.utils.ValidationUtils;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepo<E>> implements CommonService<E> {

    protected final R repo;

    protected AbstractService(R repo) {
        this.repo = repo;
    }

    @Override
    public void write(E entity) throws ValidationException, ClassNotFoundException {
        ValidationUtils.validate(entity);
        repo.save(entity);
    }

    @Override
    public Iterable<E> getAll() {
        return repo.findAll();
    }

    @Override
    public void erase(E entity) throws NotFoundException {
        if (!repo.existsById(entity.getId()))
            throw new NotFoundException("Удаляемый элемент не найден");
        repo.delete(entity);
    }

    @Override
    public void erase(Long id) throws NotFoundException {
        if (!repo.existsById(id))
            throw new NotFoundException("Удаляемый элемент не найден");
        repo.deleteById(id);
    }
}