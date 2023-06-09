package teachin.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Moonlighter extends AbstractEntity {
    @ApiModelProperty(notes = "ОГРН", required = true)
    @NotNull
    @Pattern(regexp = "^\\d{15}$", message = "ОГРН совместительства должен содержать только 15 цифровых символов")
    private String ogrn;

    @ApiModelProperty(notes = "ИНН", required = true)
    @NotNull
    @Pattern(regexp = "^(\\d{10})|(\\d{12})$", message = "ИНН совместительства может состоять из 10 или 12 цифровых символов")
    private String inn;

    @ApiModelProperty(notes = "Список преподавателей из техникума")
    @JsonIgnore
    @ManyToMany(mappedBy = "moonlighters")
    private Set<Teacher> teachers = new HashSet<>();
}
