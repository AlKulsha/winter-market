package ru.kulsha.wintermarket.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kulsha.wintermarket.api.ResourceNotFoundException;
import ru.kulsha.wintermarket.core.entities.User;
import ru.kulsha.wintermarket.core.services.OrderService;
import ru.kulsha.wintermarket.core.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private UserService userService;
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal){  //TODO чем заменить пригципал, для дальнейшей работы (токен не приходит)
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(()->new ResourceNotFoundException("User is not found"));
        orderService.createOrder(user);
    }
}
