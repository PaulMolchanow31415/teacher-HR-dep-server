package teachin.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "moonlighter")
public class Moonlighter extends AbstractEntity {
    @NotNull
    @NotBlank
    private String OGRN;

    @NotNull
    @NotBlank
    private String INN;
}
