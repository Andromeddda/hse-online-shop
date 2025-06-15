package hse.andromeddda.kafka;

import hse.andromeddda.service.PaymentService;
import hse.andromeddda.dto.PaymentRequest;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;   /* for logging */

/*
    Listener for new orders
*/
@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentKafkaListener
{
    private final PaymentService paymentService;

    @KafkaListener(topics = "new-order", containerFactory = "kafkaListenerContainerFactory")
    public void listenForPaymentRequests(PaymentRequest request)
    {
        /* log event */
        log.info("Received payment request for orderId: {}", request.orderId());

        /* process payment via service */
        try {
            paymentService.processPayment(request);
        } catch (Exception e) {
            log.error("Error processing payment for orderId: {}. Error: {}", request.orderId(), e.getMessage());
        }
    }
}