package teachin.server.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "moonlighter")
public class Moonlighter extends AbstractEntity {
    @ApiModelProperty(notes = "ОГРН", required = true)
    @NotNull
    @Pattern(regexp = "^\\d{15}$", message = "ОГРН совместительства должен содержать только 15 цифровых символов")
    private String OGRN;

    @ApiModelProperty(notes = "ИНН", required = true)
    @NotNull
    @Pattern(regexp = "^(\\d{10})|(\\d{12})$", message = "ИНН совместительства может состоять из 10 или 12 цифровых символов")
    private String INN;
}
