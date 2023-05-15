package teachin.server.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Passport extends AbstractPassport {
    @ApiModelProperty(notes = "Преподаватель")
    @OneToOne
    private Teacher teacher;
}
