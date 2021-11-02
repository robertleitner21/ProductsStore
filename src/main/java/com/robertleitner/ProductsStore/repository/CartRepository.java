package com.robertleitner.ProductsStore.repository;

import com.robertleitner.ProductsStore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
