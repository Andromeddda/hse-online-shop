package hse.andromeddda.dto;

import java.math.BigDecimal;

/*
    DTO for API
    Produced by PAYMENT SERVICE
    Consumed by CLIENT
*/
public record BalanceResponse(
        Long userId,
        BigDecimal balance
)
{ }