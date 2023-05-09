package teachin.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discipline")
public class Discipline extends AbstractEntity {
    @NotNull
    @Length(min = 2, max = 255, message = "Название дисциплины должно быть в диапазоне от 2 - 255 символов")
    private String name;
}
