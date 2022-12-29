package ru.kulsha.wintermarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kulsha.wintermarket.dto.ProductDto;
import ru.kulsha.wintermarket.entities.Cart;
import ru.kulsha.wintermarket.entities.Product;
import ru.kulsha.wintermarket.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private CartService cartService;

    @GetMapping
    public Cart showCart(){
        return cartService.showCart();
    }

    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long id){
        cartService.addProduct(id);
    }

    @GetMapping("/delete/{id}")
    public  void deleteProductById(@PathVariable Long id){
        cartService.deleteById(id);
    }
}
