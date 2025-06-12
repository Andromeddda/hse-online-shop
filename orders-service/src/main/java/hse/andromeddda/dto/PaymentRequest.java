package hse.andromeddda.dto;

import java.math.BigDecimal;

public record PaymentRequest(
        String messageId,
        Long orderId,
        Long userId,
        BigDecimal amount
    )
{ }