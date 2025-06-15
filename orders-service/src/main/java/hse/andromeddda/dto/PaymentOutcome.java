package hse.andromeddda.dto;

/*
    DTO for Kafka
    Produced by PAYMENT SERVICE
    Consumed by ORDERS SERVICE
*/
public record PaymentOutcome(
        String messageId,
        Long orderId,
        String status
)
{ }