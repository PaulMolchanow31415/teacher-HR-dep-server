package teachin.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import teachin.server.entity.AbstractEntity;
import teachin.server.res.BaseRes;
import teachin.server.res.EntityRes;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface CommonController <E extends AbstractEntity> {
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('write')")
    ResponseEntity<EntityRes<E>> save(@RequestBody @Valid E entity);

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('read')")
    ResponseEntity<BaseRes> getAll();

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('write')")
    ResponseEntity<BaseRes> update(@RequestBody @Valid E entity);

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('write')")
    ResponseEntity<BaseRes> delete(@RequestBody @Valid E entity);

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('write')")
    ResponseEntity<BaseRes> deleteById(@PathVariable @NotNull Long id);
}
