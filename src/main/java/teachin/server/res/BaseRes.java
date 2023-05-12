package teachin.server.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class BaseRes {
    @ApiModelProperty(notes = "Статус")
    private boolean success;

    @ApiModelProperty(notes = "Ответ")
    private String message;
}
