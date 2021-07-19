package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseEntity{

    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String numberPhone;
    @NotNull
    private boolean invoice;

    @ManyToMany(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
    private Set<Role> roles;

    @OneToOne
    private UserInvoiceDetails details;
}
