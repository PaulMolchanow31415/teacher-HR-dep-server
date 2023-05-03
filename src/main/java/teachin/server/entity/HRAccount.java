package teachin.server.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "HRAccount")
public class HRAccount extends AbstractEntity {
    @NotNull
    @Size(min = 2, max = 32, message = "Длина логина должна быть не меньше 2 и не длиннее 32")
    @NotNull
    private String login;

    @NotNull
    @Size(min = 4, max = 255, message = "Длина логина должна быть не меньше 4 и не длиннее 255")
    private String password;

    @NotNull
    private Permission permission;

    @NotNull
    private Role role;
}
