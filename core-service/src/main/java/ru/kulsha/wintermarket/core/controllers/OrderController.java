package ru.kulsha.wintermarket.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kulsha.wintermarket.api.OrderDto;
import ru.kulsha.wintermarket.core.converters.OrderConverter;
import ru.kulsha.wintermarket.core.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username){
        orderService.createOrder(username);
    }

    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username){
        return orderService.findByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
