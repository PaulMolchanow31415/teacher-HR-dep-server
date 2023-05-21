package teachin.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Discipline extends AbstractEntity {
    @ApiModelProperty(notes = "Название дисциплины", example = "Теория вероятностей", required = true)
    @NotNull
    @NotBlank(message = "Название дисциплины не должно содерать только пробелы")
    @Length(min = 2, max = 255, message = "Название дисциплины должно быть в диапазоне от 2 - 255 символов")
    private String name;

    @ApiModelProperty(notes = "Список преподавателей, занятых этой дисциплиной")
    @JsonIgnore
    @ManyToMany(mappedBy = "disciplines")
    private Set<Vacancy> vacancies = new HashSet<>();
}
