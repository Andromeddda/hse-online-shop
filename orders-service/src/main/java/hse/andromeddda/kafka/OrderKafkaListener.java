package hse.andromeddda.kafka;

import hse.andromeddda.dto.PaymentOutcome;
import hse.andromeddda.service.OrdersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;   /* for logging */

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/*
    Listener for payment results
*/
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderKafkaListener
{
    private final OrdersService ordersService;

    @KafkaListener(topics = "payment-outcome", containerFactory = "kafkaListenerContainerFactory")
    public void listenForPaymentOutcomes(PaymentOutcome outcome)
    {
        /* log event */
        log.info("Received payment outcome for orderId: {}. Status: {}", outcome.orderId(), outcome.status());

        /* update order status via service */
        try {
            ordersService.updateOrderStatus(outcome);
        } catch (Exception e) {
            log.error("Error updating status for orderId: {}. Error: {}", outcome.orderId(), e.getMessage());
        }
    }
}