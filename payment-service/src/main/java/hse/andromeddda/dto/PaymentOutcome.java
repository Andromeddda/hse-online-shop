package hse.andromeddda.dto;
import java.math.BigDecimal;

/*
    DTO for Kafka
*/
public record PaymentOutcome(
        String messageId,
        Long orderId,
        String status
)
{ }