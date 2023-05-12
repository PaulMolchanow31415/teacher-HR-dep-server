package teachin.server.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {
    @ApiModelProperty(notes = "Логин", required = true)
    private String username;

    @ApiModelProperty(notes = "Пароль", required = true)
    private String password;
}
