package com.robertleitner.ProductsStore.service;

import com.robertleitner.ProductsStore.exception.ProductException;
import com.robertleitner.ProductsStore.model.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {

    public Cart addToCart(Cart cart) throws ProductException;

}
