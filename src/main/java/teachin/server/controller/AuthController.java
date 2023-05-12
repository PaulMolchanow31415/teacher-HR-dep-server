package teachin.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import teachin.server.entity.HRAccount;
import teachin.server.req.LoginReq;
import teachin.server.res.BaseRes;
import teachin.server.service.AuthService;

import javax.validation.Valid;

@Api(description = "Контроллер для управления аккауном")
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @ApiOperation("Вход в аккаунт")
    @PostMapping("/login")
    public ResponseEntity<BaseRes> login(@RequestBody LoginReq loginReq) {
        try {
            return ResponseEntity.ok(service.login(loginReq));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("Создание нового аккаунта")
    @PostMapping("/create_account")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<BaseRes> register(@RequestBody @Valid HRAccount account) {
        try {
            service.createAccount(account);
            return ResponseEntity.ok(new BaseRes(true, "Пользователь успешно создан"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("Отключение аккаунта, (при срабатывании, аккаунт становится недейстивительным)")
    @PutMapping("/activate")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<BaseRes> activate(@RequestParam String username) {
        try {
            service.activate(username);
            return ResponseEntity.ok(new BaseRes(true, "Аккаунт активирован"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @ApiOperation("Активация аккаунта")
    @PutMapping("/disable")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<BaseRes> disable(@RequestParam String username) {
        try {
            service.disable(username);
            return ResponseEntity.ok(new BaseRes(true, "Аккаунт отключен"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }
}
