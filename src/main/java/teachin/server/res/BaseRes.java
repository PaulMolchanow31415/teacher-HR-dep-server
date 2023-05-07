package teachin.server.res;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class BaseRes {
    private boolean success;
    private String message;
}
