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
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vacancy")
public class Vacancy extends AbstractEntity {
    @NotNull
    @NotBlank
    @Length(min = 3, message = "Название вакансии должно содержать не менее 3 символов")
    private String name;

    @JsonIgnore
    @OneToMany(orphanRemoval = true)
    private List<Discipline> disciplines;
}
