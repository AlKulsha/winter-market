package ru.kulsha.wintermarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.kulsha.wintermarket.converters.ProductConverter;
import ru.kulsha.wintermarket.model.Cart;
import ru.kulsha.wintermarket.entities.Product;
import ru.kulsha.wintermarket.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private final ProductService productService;
    private final ProductConverter productConverter;

    @PostConstruct
    public void init(){
        cart = new Cart();
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public void addProduct(Long id){
        Product product = productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found"));
        cart.add(product);
    }

    public void clear() {
        cart.clear();
    }

    public void remove(Long productId){
       cart.remove(productId);
    }

}
