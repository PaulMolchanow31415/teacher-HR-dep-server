package teachin.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@MappedSuperclass
abstract public class AbstractPassport extends AbstractEntity {
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
    @Column(name = "date_of_issue", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfIssue;

    @ApiModelProperty(notes = "Кем был выдан", required = true)
    @NotNull
    @NotBlank(message = "Строка выдачи паспорта не должна содержать только пробелы")
    @Length(min = 2, message = "Длина строки выдачи паспорта должна быть не меньше 2 и состоять только из непробельных символов")
    @Column(name = "issuing_authority")
    private String issuingAuthority;
}
