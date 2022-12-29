package ru.kulsha.wintermarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.kulsha.wintermarket.converters.ProductConverter;
import ru.kulsha.wintermarket.dto.ProductDto;
import ru.kulsha.wintermarket.entities.Cart;
import ru.kulsha.wintermarket.entities.Product;
import ru.kulsha.wintermarket.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;

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

    public void addProduct(Long id){
        Product product = productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found"));
        cart.addProduct(product);
    }

    public Cart showCart(){
        return cart.showCart();
    }

    public void deleteById(Long id){
        Product product = productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found"));
        cart.deleteProduct(product);
    }

}
