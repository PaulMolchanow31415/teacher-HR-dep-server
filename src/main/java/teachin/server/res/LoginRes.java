package teachin.server.res;

import lombok.Getter;

@Getter
public class LoginRes extends BaseRes {
    private final String token;
    private final long expiredIn;

    public LoginRes(String token, long expiredIn) {
        super(true, "Токен доступа");
        this.token = token;
        this.expiredIn = expiredIn;
    }
}
