package teachin.server.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@MappedSuperclass
public abstract class AbstractEmployee extends AbstractEntity {
    @NotBlank
    @Pattern(regexp = "^[A-Z|А-Я][a-z|а-я]{2,32}$", message = "Имя сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы")
    protected String name;

    @NotBlank
    @Pattern(regexp = "^[A-Z|А-Я][a-z|а-я]{2,32}$", message = "Фамилия сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы")
    protected String surname;

    @NotBlank
    @Pattern(regexp = "^[A-Z|А-Я][a-z|а-я]{2,32}$", message = "Отчество сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы")
    protected String patronymic;

    @NotNull
    @OneToOne
    protected Passport passport;

    @JsonIgnore
    @OneToMany(orphanRemoval = true)
    protected List<Moonlighter> moonlighters;
}
