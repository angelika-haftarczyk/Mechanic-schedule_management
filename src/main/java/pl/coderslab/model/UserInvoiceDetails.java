package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserInvoiceDetails extends BaseEntity{


    private String nip;
    private String regon;
    @NotNull
    private String companyName;
    @NotNull
    private String companyAddress;
    @OneToOne
    private User user;

}
