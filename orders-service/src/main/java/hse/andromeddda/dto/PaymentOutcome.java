package hse.andromeddda.dto;

/*
    Produced by PAYMENT SERVICE
    Consumed by ORDERS SERVICE
*/
public record PaymentOutcome(
        String messageId,
        Long orderId,
        String status
)
{ }