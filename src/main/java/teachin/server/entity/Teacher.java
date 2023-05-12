package teachin.server.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher extends AbstractEmployee {
    @ApiModelProperty(notes = "Занимаемая должность", required = true)
    @NotNull(message = "У преподавателя обязательно поле должности")
    @OneToOne
    private Vacancy vacancy;

    @ApiModelProperty(notes = "Ученая степень", required = true)
    @NotNull
    @Enumerated(STRING)
    private ScientificDegree scientificDegree;

    @ApiModelProperty(notes = "Загруженность в часах", required = true)
    @NotNull
    @Min(value = 1, message = "Количество часов должно быть не меньше одного")
    private Integer hours;

    @ApiModelProperty(notes = "Название общественной работы")
    @Length(min = 2, message = "Длина строки общественной работы должна быть не меньше 2 символов")
    private String socialWork;
}
