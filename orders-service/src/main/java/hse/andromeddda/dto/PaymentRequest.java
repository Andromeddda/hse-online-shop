package hse.andromeddda.dto;

import java.math.BigDecimal;

/*
    Data Transfer Object (DTO) from OrdersService to PaymentService.
    Request on paying an order.
*/
public record PaymentRequest(
        String messageId,
        Long orderId,
        Long userId,
        BigDecimal amount
    )
{ }