package teachin.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy extends AbstractEntity {
    @ApiModelProperty(notes = "Название", required = true)
    @NotNull
    @NotBlank(message = "Название должности не должно содержать только пробелы")
    @Length(min = 3, message = "Название должности должно содержать не менее 3 символов")
    private String name;

    @ApiModelProperty(notes = "Список дисциплин")
    @JsonIgnore
    @Size(min = 1, message = "У должности должна быть хотя бы одна дисциплина")
    @ManyToMany(cascade = {PERSIST, MERGE}, fetch = LAZY)
    @JoinTable(name = "vacancy_discipline",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private Set<Discipline> disciplines = new HashSet<>();

    @ApiModelProperty(notes = "Преподаватель")
    @OneToOne
    private Teacher teacher;
}
