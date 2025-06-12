package hse.andromeddda.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(
            Long userId,
            BigDecimal amount
    )
{ }