package pl.coderslab.service;

import pl.coderslab.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product setAvailable(Product product, boolean available);
    List<Product> findAll();
    Product saveProduct(Product product);
    List<Product> findAllActive();
    Product getProductById(Long id);
}
