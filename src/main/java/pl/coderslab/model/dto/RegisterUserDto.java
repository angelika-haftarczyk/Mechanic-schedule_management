package pl.coderslab.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class RegisterUserDto { //obiekt DTO czyli Data Transfer Object, wykorzystywany do mapowania formularzy html na obiekt javy
    //  some DTO from browser
    //
    //            v
    //
    //Spring @Controller method
    //
    //            v
    //
    //  Spring @Service method
    // mozliwa walidacja w DTO

    @NotNull
    @Email
    private String email;
    @NotNull
    private String login;
    @Min(6)
    private String password;
    @Min(6)
    private String confirm_password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String numberPhone;

}