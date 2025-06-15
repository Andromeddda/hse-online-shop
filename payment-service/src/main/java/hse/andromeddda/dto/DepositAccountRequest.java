package hse.andromeddda.dto;

import java.math.BigDecimal;

/*
    DTO for API
*/
public record CreateAccountRequest(
        Long userId,
        BigDecimal amount
)
{ }