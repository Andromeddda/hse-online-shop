package hse.andromeddda.dto;

import java.math.BigDecimal;

/*
    Produced by ORDERS SERVICE
    Consumed by PAYMENT SERVICE
*/
public record PaymentRequest(
        String messageId,
        Long orderId,
        Long userId,
        BigDecimal amount
    )
{ }