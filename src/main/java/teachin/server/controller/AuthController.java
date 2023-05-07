package teachin.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teachin.server.entity.HRAccount;
import teachin.server.req.LoginReq;
import teachin.server.res.BaseRes;
import teachin.server.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<BaseRes> login(@RequestBody LoginReq loginReq) {
        try {
            return ResponseEntity.ok(service.login(loginReq));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

   @PostMapping("/create_account")
    public ResponseEntity<BaseRes> register(@RequestBody @Valid HRAccount account) {
        try{
            service.createAccount(account);
            return ResponseEntity.ok(new BaseRes(true, "Пользователь успешно создан"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }

    @DeleteMapping("/disable")
    public ResponseEntity<BaseRes> disable(String username) {
        try {
            service.disable(username);
            return ResponseEntity.ok(new BaseRes(true, "Аккаунт отключен"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseRes(false, e.getMessage()));
        }
    }
}
