package hse.andromeddda.dto;
import java.math.BigDecimal;

/*
    DTO for Kafka
    Produced by PAYMENT SERVICE
    Consumed by ORDERS SERVUCE
*/
public record PaymentOutcome(
        String messageId,
        Long orderId,
        String status
)
{ }