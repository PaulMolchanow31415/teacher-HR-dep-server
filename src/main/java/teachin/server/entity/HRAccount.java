package teachin.server.entity;

import io.swagger.annotations.ApiModelProperty;
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

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.Table;
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
    @ApiModelProperty(notes = "Логин", required = true)
    @NotNull
    @NotBlank(message = "Логин не должен содержать только пробелы")
    @Length(min = 2, max = 32, message = "Длина логина должна быть не меньше 2 и не длиннее 32")
    private String username;

    @ApiModelProperty(notes = "Пароль", required = true)
    @NotNull
    @NotBlank(message = "Пароль не должен содержать только пробелы")
    @Length(min = 4, max = 255, message = "Длина пароля должна быть не меньше 4 и не длиннее 255")
    private String password;

    @ApiModelProperty(notes = "Роль", example = "DEAN или ADMIN", required = true)
    @NotNull
    @Enumerated(STRING)
    private Role role;

    @ApiModelProperty(notes = "Статус действия аккаунта")
    @Enumerated(STRING)
    private Status status;

    @ApiModelProperty(notes = "Дата создания")
    @CreatedDate
    private Date created;

    @ApiModelProperty(notes = "Дата обновления")
    @LastModifiedDate
    private Date updated;
}
