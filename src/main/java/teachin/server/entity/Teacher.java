package teachin.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "teacher")
public class Teacher extends AbstractEmployee {
    @NotNull
    @OneToOne
    private Vacancy vacancy;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ScientificDegree scientificDegree;

    @NotNull
    @Min(1)
    private Integer hours;

    @Length(min = 2, message = "Длина строки общественной работы должна превосходить 2 символа")
    private String socialWork;
}
