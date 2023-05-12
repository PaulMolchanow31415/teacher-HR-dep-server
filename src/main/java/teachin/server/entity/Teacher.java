package teachin.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher extends AbstractEmployee {
    @NotNull(message = "У преподавателя обязательно поле должности")
    @OneToOne
    private Vacancy vacancy;

    @NotNull
    @Enumerated(STRING)
    private ScientificDegree scientificDegree;

    @NotNull
    @Min(value = 1, message = "Количество часов должно быть не меньше одного")
    private Integer hours;

    @Length(min = 2, message = "Длина строки общественной работы должна быть не меньше 2 символов")
    private String socialWork;
}
