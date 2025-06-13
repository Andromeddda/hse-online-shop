package hse.andromeddda.dto;

import java.math.BigDecimal;

/*
    Produced by CLIENT
    Consumed by ORDERS SERVICE
*/
public record CreateOrderRequest(
            Long userId,
            BigDecimal amount
    )
{ }