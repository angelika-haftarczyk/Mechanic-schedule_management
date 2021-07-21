package pl.coderslab.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.REGON;
import pl.coderslab.model.UserInvoiceDetails;

import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserDto {



    @NotNull
    @Email
    private String email;
    private String login;
//    @Length(min=6)
    private String newPassword;
//    @Length(min=6)
    private String confirmPassword;

    private String actualPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String numberPhone;

//    @NotNull
//    @NIP
    private String nip;
//    @REGON
    private String regon;
    @NotNull
    private String companyName;
    @NotNull
    private String companyAddress;

    @NotNull
    private boolean invoice;

}
