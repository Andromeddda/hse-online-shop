package hse.andromeddda.dto;

import java.math.BigDecimal;

/*
    Data Transfer Object (DTO) from client to OrdersService.
    Request on creating an order.
*/
public record CreateOrderRequest(
            Long userId,
            BigDecimal amount
    )
{ }