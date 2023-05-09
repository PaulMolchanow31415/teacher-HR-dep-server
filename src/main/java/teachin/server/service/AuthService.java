package teachin.server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import teachin.server.entity.HRAccount;
import teachin.server.exception.AlreadyExistException;
import teachin.server.exception.NotFoundException;
import teachin.server.exception.ValidationException;
import teachin.server.repo.HRAccountRepo;
import teachin.server.req.LoginReq;
import teachin.server.res.LoginRes;
import teachin.server.security.JWTokenProvider;
import teachin.server.security.Status;
import teachin.server.utils.ValidationUtils;

@Service
public class AuthService {
    @Value("${jwt.expiration}")
    private long expiration;
    private final HRAccountRepo repo;
    private final AuthenticationManager manager;
    private final JWTokenProvider provider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(HRAccountRepo repo, AuthenticationManager manager, JWTokenProvider provider, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.manager = manager;
        this.provider = provider;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginRes login(LoginReq loginReq) throws NotFoundException {
        manager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        repo.findByUsername(loginReq.getUsername())
                .orElseThrow(() -> new NotFoundException("Неверные имя пользователя и пароль"));
        String token = provider.createToken(loginReq.getUsername());
        return new LoginRes(token, expiration);
    }

    public void createAccount(HRAccount account) throws ValidationException, AlreadyExistException {
        ValidationUtils.validate(account);
        if (repo.findByUsername(account.getUsername()).isPresent())
            throw new AlreadyExistException("Пользователь с таким именем уже создан");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setStatus(Status.ACTIVE);
        repo.save(account);
    }

    public void activate(String username) throws NotFoundException {
        HRAccount account = repo.findByUsername(username).orElseThrow(() ->
                new NotFoundException("Пользователя с таким именем не существует"));
        account.setStatus(Status.ACTIVE);
        repo.save(account);
    }

    public void disable(String username) throws NotFoundException {
        HRAccount account = repo.findByUsername(username).orElseThrow(() ->
                new NotFoundException("Пользователя с таким именем не существует"));
        account.setStatus(Status.DISABLE);
        repo.save(account);
    }
}
