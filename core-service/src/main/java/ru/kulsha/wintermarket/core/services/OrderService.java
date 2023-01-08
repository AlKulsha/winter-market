package ru.kulsha.wintermarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kulsha.wintermarket.api.CartDto;
import ru.kulsha.wintermarket.core.entities.Order;
import ru.kulsha.wintermarket.core.entities.OrderItem;
import ru.kulsha.wintermarket.core.entities.User;
import ru.kulsha.wintermarket.core.repositories.OrderRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    @Transactional
    public void createOrder(User user){
        CartDto cartDto = null;  //TODO: cartServiceIntegration.getCurrentcart();
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(
                cartItem -> new OrderItem(
                        productService.findById(cartItem.getProductId()).get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
        //TODO: cartServiceIntegration.clear();  to clear the cart in CartMS мб срвзу на фронте функцию очистки корзины

    }
}

