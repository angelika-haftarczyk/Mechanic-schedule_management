package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Note;
import pl.coderslab.model.UserInvoiceDetails;

@Repository
public interface DetailsRepository extends JpaRepository<UserInvoiceDetails, Long> {

}
