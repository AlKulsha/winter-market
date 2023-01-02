package ru.kulsha.wintermarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kulsha.wintermarket.model.Cart;
import ru.kulsha.wintermarket.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart showCart(){
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long id){
        cartService.addProduct(id);
    }

    @GetMapping("/clear")
    public  void clearCart(){
        cartService.clear();
    }

    @GetMapping("/remove/{id}")
    public  void removeFromCart(@PathVariable Long id){
        cartService.remove(id);
    }
}
