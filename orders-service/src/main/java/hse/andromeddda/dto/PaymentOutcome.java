package hse.andromeddda.dto;

/*
    Data Transfer Object (DTO) from PaymentService to OrdersService.
    Response on paying an order.
*/
public record PaymentOutcome(
        String messageId,
        Long orderId,
        String status
)
{ }