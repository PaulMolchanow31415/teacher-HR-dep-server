package teachin.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discipline")
public class Discipline extends AbstractEntity {
    @NotNull
    @NotBlank(message = "Название дисциплины не должно содерать только пробелы")
    @Length(min = 2, max = 255, message = "Название дисциплины должно быть в диапазоне от 2 - 255 символов")
    private String name;

    @JsonIgnore
    @OneToMany(fetch = LAZY)
    private Set<Teacher> teachers;
}
