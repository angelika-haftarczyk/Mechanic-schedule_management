package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.REGON;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserInvoiceDetails extends BaseEntity{

//    @NotNull
//    @NIP
    private String nip;
//    @REGON
    private String regon;
    @NotNull
    private String companyName;
    @NotNull
    private String companyAddress;
    @OneToOne
    private User user;

}
