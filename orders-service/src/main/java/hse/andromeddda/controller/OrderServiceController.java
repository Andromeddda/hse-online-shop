package hse.andromeddda.controller;

/* PostMapping, GetMapping, RequestMapping, RestController, RequestBody, PathVariable */
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import hse.andromeddda.dto.*; /* CreateOrderRequest, OrderResponse, PaymentRequest, PaymentOutcome */
import hse.andromeddda.service.OrdersService;
import hse.andromeddda.model.Order;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/*
    REST controller for the service
*/
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderServiceController
{
    /* facade for DB and Outbox */
    private final OrdersService ordersService;

    /*
        Create order via POST-request
        EXAMPLE: { "userId": 123, "amount": 99.99 }
    */
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request)
    {
        Order order = ordersService.createOrder(request);
        return ResponseEntity.ok(toResponse(order));
    }

    /*
        Get all orders of user via GET-request
    */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable Long userId)
    {
        List<OrderResponse> responses = ordersService.getOrdersByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /*
        Get order via GET-request
    */
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId)
    {
        Order order = ordersService.getOrderById(orderId);
        return ResponseEntity.ok(toResponse(order));
    }

    /* Convert order to OrderResponse DTO */
    private OrderResponse toResponse(Order order)
    {
        return new OrderResponse(order.getId(), order.getUserId(), order.getAmount(), order.getStatus(), order.getCreatedAt());
    }
}