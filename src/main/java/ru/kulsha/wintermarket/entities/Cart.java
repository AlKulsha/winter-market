package ru.kulsha.wintermarket.entities;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.kulsha.wintermarket.dto.ProductDto;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Scope("singleton")
@Data
public class Cart {
    private List<Product> productsList = new ArrayList<>();

    public Cart() {
    }

    public void addProduct(Product product){
        productsList.add(product);
    }

    public Cart showCart(){
        return this;
    }

    public void deleteProduct(Product product){
        productsList.remove(product);
    }
}

