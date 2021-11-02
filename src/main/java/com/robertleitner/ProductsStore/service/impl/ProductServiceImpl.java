package com.robertleitner.ProductsStore.service.impl;

import com.robertleitner.ProductsStore.consts.ProductResponse;
import com.robertleitner.ProductsStore.model.Product;
import com.robertleitner.ProductsStore.repository.ProductRepo;
import com.robertleitner.ProductsStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;


    @Override
    public List<Product> getAllProducts() {

        return productRepo.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        if (product != null)
            return productRepo.save(product);
        return new Product();
    }

    @Override
    public List<Product> searchProduct(Map<String, String> allRequestParams) {

        if (!CollectionUtils.isEmpty(allRequestParams)) {
            for (Entry<String, String> entry : allRequestParams.entrySet()){
                String param = entry.getKey();
                String val = entry.getValue();
                if (ProductResponse.PRODUCT_NAME.equalsIgnoreCase(param) && !StringUtils.hasLength(val)) {
                    return productRepo.findByProductName(val);
                }
            }
        }

        return null;
    }

    @Override
    public String removeProduct(long id) {
        int result = productRepo.removeById(id);
        if (result != 0)
            return ProductResponse.SUCCESS;
        return ProductResponse.FAILED;
    }
}
