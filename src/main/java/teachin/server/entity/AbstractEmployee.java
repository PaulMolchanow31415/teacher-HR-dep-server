package teachin.server.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@MappedSuperclass
public abstract class AbstractEmployee extends AbstractEntity {
    @ApiModelProperty(notes = "Имя", example = "Иван")
    @NotBlank
    @Pattern(regexp = "^[A-Z|А-Я][a-z|а-я]{2,32}$", message = "Имя сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы")
    protected String name;

    @ApiModelProperty(notes = "Фамилия", example = "Иванов")
    @NotBlank
    @Pattern(regexp = "^[A-Z|А-Я][a-z|а-я]{2,32}$", message = "Фамилия сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы")
    protected String surname;

    @ApiModelProperty(notes = "Отчество", example = "Иванович")
    @NotBlank
    @Pattern(regexp = "^[A-Z|А-Я][a-z|а-я]{2,32}$", message = "Отчество сотрудника должно иметь 2 - 32 символов и начинаться с заглавной буквы")
    protected String patronymic;

    @ApiModelProperty(notes = "Паспорт", required = true)
    @NotNull
    @OneToOne
    protected Passport passport;

    @ApiModelProperty(notes = "Совместительства")
    @JsonIgnore
    @OneToMany(fetch = LAZY)
    protected List<Moonlighter> moonlighters;
}
