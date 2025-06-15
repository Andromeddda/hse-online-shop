package hse.andromeddda.dto;
import java.math.BigDecimal;

/*
    DTO for Kafka
*/
public record PaymentRequest(
        String messageId,
        Long orderId,
        Long userId,
        BigDecimal amount
)
{ }