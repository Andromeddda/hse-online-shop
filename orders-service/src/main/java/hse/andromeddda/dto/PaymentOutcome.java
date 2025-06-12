package hse.andromeddda.dto;

public record PaymentOutcome(
        String messageId,
        Long orderId,
        String status
)
{ }