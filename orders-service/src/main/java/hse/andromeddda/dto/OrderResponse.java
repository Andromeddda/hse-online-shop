package hse.andromeddda.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import hse.andromeddda.model.OrderStatus;

/*
    Data Transfer Object (DTO) from OrdersService to client.
    Response on creating an order.
*/
public record OrderResponse(
        Long orderId,
        Long userId,
        BigDecimal amount,
        OrderStatus status,
        LocalDateTime createdAt
    )
{ }