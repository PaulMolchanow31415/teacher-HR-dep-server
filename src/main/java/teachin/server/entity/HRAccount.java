package teachin.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hr_account")
public class HRAccount extends AbstractEntity {
    @NotNull
    @NotBlank
    @Length(min = 2, max = 32, message = "Длина логина должна быть не меньше 2 и не длиннее 32")
    private String username;

    @NotNull
    @NotBlank
    @Length(min = 4, max = 255, message = "Длина пароля должна быть не меньше 4 и не длиннее 255")
    private String password;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date updated;
}
