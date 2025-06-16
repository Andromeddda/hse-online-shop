package hse.andromeddda.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;

import hse.andromeddda.service.OrdersService;
import hse.andromeddda.dto.PaymentOutcome;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;   /* for logging */
import lombok.SneakyThrows;

/*
    Listener for payment results
*/
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderKafkaListener
{
    private final OrdersService ordersService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(topics = "payment-outcome", containerFactory = "kafkaListenerContainerFactory")
    public void listenForPaymentOutcomes(String outcomeString)
    {
        PaymentOutcome outcome = objectMapper.readValue(outcomeString, PaymentOutcome.class);

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