package teachin.server.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@MappedSuperclass
abstract public class AbstractEntity implements Serializable {
    @ApiModelProperty(notes = "Идентификатор")
    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected Long id;
}
