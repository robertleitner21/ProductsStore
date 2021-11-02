package com.robertleitner.ProductsStore.service.impl;


import com.robertleitner.ProductsStore.exception.ProductException;
import com.robertleitner.ProductsStore.model.Cart;
import com.robertleitner.ProductsStore.repository.CartRepository;
import com.robertleitner.ProductsStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Cart addToCart(Cart cart) throws ProductException {
        if (cart.getProductId() <= 0)
            throw new ProductException("Product id is not valid");
        if (cart.getQuantity() <= 0)
            throw new ProductException("Quantity cannot be zero");
        cartRepository.save(cart);
        return new Cart();
    }

}
