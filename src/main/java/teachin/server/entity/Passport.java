package teachin.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "passport")
public class Passport extends AbstractEntity {
    @NotNull
    @Pattern(regexp = "^\\d{4}$", message = "Серия паспорта должна состоять только из 4 цифровых символов")
    private String series;

    @NotNull
    @Pattern(regexp = "^\\d{6}$", message = "Номер паспорта должен состоять только из 6 цифровых символов")
    private String number;

    @NotNull
    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @NotNull
    @NotBlank(message = "Строка выдачи паспорта не должна содержать только пробелы")
    @Min(value = 2, message = "Длина строки выдачи паспорта должна быть не меньше 2 и состоять только из непробельных символов")
    @Column(name = "issuing_authority")
    private String issuingAuthority;
}
