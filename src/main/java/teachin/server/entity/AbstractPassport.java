package teachin.server.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@MappedSuperclass
public class AbstractPassport extends AbstractEntity {
    @ApiModelProperty(notes = "Серия", required = true)
    @NotNull
    @Pattern(regexp = "^\\d{4}$", message = "Серия паспорта должна состоять только из 4 цифровых символов")
    private String series;

    @ApiModelProperty(notes = "Номер", required = true)
    @NotNull
    @Pattern(regexp = "^\\d{6}$", message = "Номер паспорта должен состоять только из 6 цифровых символов")
    private String number;

    @ApiModelProperty(notes = "Дата выдачи", required = true)
    @NotNull
    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @ApiModelProperty(notes = "Кем был выдан", required = true)
    @NotNull
    @NotBlank(message = "Строка выдачи паспорта не должна содержать только пробелы")
    @Min(value = 2, message = "Длина строки выдачи паспорта должна быть не меньше 2 и состоять только из непробельных символов")
    @Column(name = "issuing_authority")
    private String issuingAuthority;
}
