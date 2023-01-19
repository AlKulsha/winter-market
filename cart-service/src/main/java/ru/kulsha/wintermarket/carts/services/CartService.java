package ru.kulsha.wintermarket.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.kulsha.wintermarket.api.ProductDto;
import ru.kulsha.wintermarket.api.ResourceNotFoundException;
import ru.kulsha.wintermarket.carts.integrations.ProductServiceIntegration;
import ru.kulsha.wintermarket.carts.model.Cart;


import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private final ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    public void init(){
        cart = new Cart();
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public void addProduct(Long id){
        ProductDto product = productServiceIntegration.getProductById(id);
        cart.add(product);
    }

    public void clear() {
        cart.clear();
    }

    public void remove(Long productId){
       cart.remove(productId);
    }

}
