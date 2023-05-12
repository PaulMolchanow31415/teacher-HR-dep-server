package teachin.server.controller;

import org.springframework.http.ResponseEntity;
import teachin.server.entity.AbstractEntity;
import teachin.server.res.BaseRes;
import teachin.server.res.EntityRes;
import teachin.server.res.ListRes;
import teachin.server.service.CommonService;

public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>>
        implements CommonController<E> {

    private final String NAME_SUPERCLASS = super.getClass().getSimpleName();
    private final String NAME_ENTITY = NAME_SUPERCLASS.substring(0, NAME_SUPERCLASS.length() - 10).toLowerCase();

    protected final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<EntityRes<E>> save(E entity) {
        try {
            service.write(entity);
            return ResponseEntity.ok(new EntityRes<>(true, "Data saved", entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new EntityRes<>(false, e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<BaseRes> getAll() {
        try {
            return ResponseEntity.ok(new ListRes<>("List %s".formatted(NAME_ENTITY), service.getAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<BaseRes> update(E entity) {
        try {
            service.write(entity);
            return ResponseEntity.ok(new BaseRes(true, "Changes have been made to the %s".formatted(NAME_ENTITY)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<BaseRes> deleteById(Long id) {
        try {
            service.erase(id);
            return ResponseEntity.ok(new BaseRes(true, "The %s has been deleted by id".formatted(NAME_ENTITY)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<BaseRes> delete(E entity) {
        try {
            service.erase(entity);
            return ResponseEntity.ok(new BaseRes(true, "The %s has been deleted".formatted(NAME_ENTITY)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }
}
