package pl.coderslab.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserDto {



    @NotNull
    @Email
    private String email;
    private String login;

    private String newPassword;

    private String confirmPassword;

    private String actualPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String numberPhone;


    private String nip;
    private String regon;
    @NotNull
    private String companyName;
    @NotNull
    private String companyAddress;

    @NotNull
    private boolean invoice;

}
