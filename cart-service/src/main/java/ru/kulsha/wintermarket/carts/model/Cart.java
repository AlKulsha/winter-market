package ru.kulsha.wintermarket.carts.model;

import lombok.Data;
import ru.kulsha.wintermarket.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto product){
        for(CartItem item : items){
            if(product.getId().equals(item.getProductId())){
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(CartItem.builder()
                .productId(product.getId())
                .productTitle(product.getTitle())
                .quantity(1)
                .price(product.getPrice())
                .pricePerProduct(product.getPrice()).build());
//        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    private void recalculate(){
        totalPrice = BigDecimal.ZERO;
        for (CartItem item : items) {
            totalPrice = totalPrice.add(item.getPrice());
        }
    }

    public void clear(){
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    public void remove(Long productId){
       if(items.removeIf(item -> item.getProductId().equals(productId))){
           recalculate();
       }
    }

}
