package hse.andromeddda.dto;

import java.math.BigDecimal;

/*
    DTO for Kafka
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