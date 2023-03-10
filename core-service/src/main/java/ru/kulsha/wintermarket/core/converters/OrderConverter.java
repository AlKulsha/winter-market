package ru.kulsha.wintermarket.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kulsha.wintermarket.api.OrderDto;
import ru.kulsha.wintermarket.api.OrderItemDto;
import ru.kulsha.wintermarket.core.entities.Order;
import ru.kulsha.wintermarket.core.entities.OrderItem;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setAddress(order.getAddress());
        orderDto.setPhone(order.getPhone());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setUsername(order.getUsername());
        orderDto.setItems(order.getItems().stream().map(orderItemConverter::entityToDto)
                .collect(Collectors.toList()));
        return orderDto;
    }
}
