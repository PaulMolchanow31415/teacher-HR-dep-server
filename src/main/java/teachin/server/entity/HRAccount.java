package teachin.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import teachin.server.security.Role;
import teachin.server.security.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hr_account")
public class HRAccount extends AbstractEntity {
    @NotNull
    @NotBlank(message = "Логин не должен содержать только пробелы")
    @Length(min = 2, max = 32, message = "Длина логина должна быть не меньше 2 и не длиннее 32")
    private String username;

    @NotNull
    @NotBlank(message = "Пароль не должен содержать только пробелы")
    @Length(min = 4, max = 255, message = "Длина пароля должна быть не меньше 4 и не длиннее 255")
    private String password;

    @NotNull
    @Enumerated(STRING)
    private Role role;

    @Enumerated(STRING)
    private Status status;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date updated;
}
