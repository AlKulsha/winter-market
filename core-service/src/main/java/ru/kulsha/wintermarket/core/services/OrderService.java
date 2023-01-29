package ru.kulsha.wintermarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kulsha.wintermarket.api.CartDto;
import ru.kulsha.wintermarket.core.entities.Order;
import ru.kulsha.wintermarket.core.entities.OrderItem;
import ru.kulsha.wintermarket.core.integrations.CartServiceIntegration;
import ru.kulsha.wintermarket.core.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    @Transactional
    public void createOrder(String username){
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
        Order order = new Order();
        order.setUsername(username);
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
        cartServiceIntegration.clear(username);

    }

    public List<Order> findByUsername(String username){
        return orderRepository.findByUsername(username);
    }
}

