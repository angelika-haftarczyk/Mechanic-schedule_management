package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
