package com.robertleitner.ProductsStore.controller;


import com.robertleitner.ProductsStore.model.Product;
import com.robertleitner.ProductsStore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping(value = "/product/all")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/product")
    @ResponseBody
    public List<Product> searchProduct(@RequestParam Map<String, String> allRequestParams) {
        return productService.searchProduct(allRequestParams);
    }

    @PostMapping("/product")
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }


    @DeleteMapping("/product/{id}")
    @ResponseBody
    public String removeProduct(@PathVariable("id") long id) {
        return productService.removeProduct(id);
    }
}
