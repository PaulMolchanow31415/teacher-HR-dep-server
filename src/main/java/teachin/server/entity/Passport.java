package teachin.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "passport")
public class Passport extends AbstractEntity {
    @NotNull
    @NotBlank
    private String series;

    @NotNull
    @NotBlank
    private String number;

    @NotNull
    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @NotNull
    @NotBlank
    @Column(name = "issuing_authority")
    private String issuingAuthority;
}
