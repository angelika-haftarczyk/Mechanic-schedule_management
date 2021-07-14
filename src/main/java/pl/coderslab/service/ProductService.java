package pl.coderslab.service;

import pl.coderslab.model.Product;

public interface ProductService {
    Product addProduct(Product product);
    Product setAvailable(Product product, boolean available);
}
