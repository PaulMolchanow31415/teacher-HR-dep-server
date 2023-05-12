package teachin.server.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class LoginRes extends BaseRes {
    @ApiModelProperty(notes = "Токен доступа")
    private final String token;

    @ApiModelProperty(notes = "Время истечения токена")
    private final long expiredIn;

    public LoginRes(String token, long expiredIn) {
        super(true, "Токен доступа");
        this.token = token;
        this.expiredIn = expiredIn;
    }
}
