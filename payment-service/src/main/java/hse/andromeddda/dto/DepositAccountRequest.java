package hse.andromeddda.dto;

import java.math.BigDecimal;

/*
    DTO for API
*/
public record DepositAccountRequest(
        Long userId,
        BigDecimal amount
)
{ }