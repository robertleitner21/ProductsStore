package com.robertleitner.ProductsStore.repository;

import com.robertleitner.ProductsStore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("select products from Product products where products.name = :productName")
    List<Product> findByProductName(@Param("productName") String productName);

    @Modifying
    @Transactional
    @Query("delete from Product products where products.id = :id")
    int removeById(@Param("id") Long id);
}
