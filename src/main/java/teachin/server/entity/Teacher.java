package teachin.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends AbstractEmployee {
    @ApiModelProperty(notes = "Паспорт", required = true)
    @NotNull
    @OneToOne
    protected Passport passport;

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

    @ApiModelProperty(notes = "Список совместительств с основной работой")
    @JsonIgnore
    @ManyToMany(cascade = {PERSIST, MERGE}, fetch = LAZY)
    @JoinTable(name = "employee_moonlighter",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "moonlighter_id")
    )
    protected Set<Moonlighter> moonlighters = new HashSet<>();
}
