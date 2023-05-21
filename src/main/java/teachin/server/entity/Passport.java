package teachin.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @OneToOne
    private Teacher teacher;
}
