package teachin.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teachin.server.entity.AbstractEntity;
import teachin.server.res.BaseRes;
import teachin.server.res.EntityRes;

import javax.validation.constraints.NotNull;

public interface CommonController <E extends AbstractEntity> {
    @PostMapping("/add")
    ResponseEntity<EntityRes<E>> save(@RequestBody E entity);

    @GetMapping("/list")
    ResponseEntity<BaseRes> getAll();

    @PutMapping("/update")
    ResponseEntity<BaseRes> update(@RequestBody E entity);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<BaseRes> deleteById(@NotNull @PathVariable Long id);

    @DeleteMapping("/delete")
    ResponseEntity<BaseRes> delete(@RequestBody E entity);
}
