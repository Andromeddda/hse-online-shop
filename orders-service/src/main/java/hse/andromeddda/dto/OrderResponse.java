package hse.andromeddda.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import hse.andromeddda.model.OrderStatus;

/*
    Produced by ORDERS SERVICE
    Consumed by CLIENT
*/
public record OrderResponse(
        Long orderId,
        Long userId,
        BigDecimal amount,
        OrderStatus status,
        LocalDateTime createdAt
    )
{ }