package com.robertleitner.ProductsStore.controller;

import com.robertleitner.ProductsStore.exception.ProductException;
import com.robertleitner.ProductsStore.model.Cart;
import com.robertleitner.ProductsStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    @ResponseBody
    public Cart addToCart(@RequestBody Cart cart) throws ProductException {
        return cartService.addToCart(cart);
    }
}
