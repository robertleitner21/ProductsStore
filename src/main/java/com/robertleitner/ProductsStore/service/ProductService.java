package com.robertleitner.ProductsStore.service;

import com.robertleitner.ProductsStore.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> getAllProducts();

    Product addProduct(Product product);

    List<Product> searchProduct(Map<String, String> allRequestParams);

    String removeProduct(long id);
}
