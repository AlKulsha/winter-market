package ru.kulsha.wintermarket.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kulsha.wintermarket.api.CartDto;
import ru.kulsha.wintermarket.carts.converters.CartConverter;
import ru.kulsha.wintermarket.carts.model.Cart;
import ru.kulsha.wintermarket.carts.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto showCart(){
        return cartConverter.entityToDto(cartService.getCurrentCart());
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
