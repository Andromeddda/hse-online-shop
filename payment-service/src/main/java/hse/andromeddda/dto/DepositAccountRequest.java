package hse.andromeddda.dto;

import java.math.BigDecimal;

/*
    DTO for API
    Produced by CLIENT
    Consumed by PAYMENT SERVICE
*/
public record DepositAccountRequest(
        Long userId,
        BigDecimal amount
)
{ }